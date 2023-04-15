package com.example.webnovel.book.ui;

import com.example.webnovel.book.application.BookService;
import com.example.webnovel.book.domain.book.Book;
import com.example.webnovel.book.domain.book.Episode;
import com.example.webnovel.book.dto.EpisodeResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EpisodeController.class)
class EpisodeControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;


    @DisplayName("식별자를 통해 에피소드를 가져올 수 있다")
    @WithMockUser
    @Test
    void getEpisode() throws Exception {
        // given
        given(bookService.getEpisode(1L, 1L)).willReturn(new EpisodeResponse(1L, "title", "content"));

        // when & then
        mvc.perform(get("/api/v1/books/1/episodes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("episodeId").value(1L))
                .andExpect(jsonPath("title").value("title"))
                .andExpect(jsonPath("content").value("content"));
    }

    @DisplayName("식별자를 통해 에피소드를 추가할 수 있다")
    @WithMockUser
    @Test
    void addEpisode() throws Exception {
        // given
        final Book givenBook = new Book(1L, "title", 1L, 1L, new Episode(1L, "title", "content", 1));
        given(bookService.addEpisode(1L, "title", "content", 1)).willReturn(givenBook);

        // when & then
        mvc.perform(post("/api/v1/books/1/episodes")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"title\", \"content\":\"content\"}"))
                .andExpect(status().isCreated());
    }
}
