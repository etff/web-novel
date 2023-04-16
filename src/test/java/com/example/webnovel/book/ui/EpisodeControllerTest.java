package com.example.webnovel.book.ui;

import com.example.webnovel.auth.application.AuthService;
import com.example.webnovel.auth.dto.AuthDetails;
import com.example.webnovel.book.application.BookService;
import com.example.webnovel.book.domain.book.Book;
import com.example.webnovel.book.domain.book.Episode;
import com.example.webnovel.book.domain.book.type.BookStatus;
import com.example.webnovel.book.dto.EpisodeResponse;
import com.example.webnovel.user.domain.User;
import com.example.webnovel.user.domain.type.Role;
import com.example.webnovel.user.domain.type.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
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

    @MockBean
    private AuthService authService;

    private AuthDetails authDetails;

    {
        final User user = new User(1L, "test@test.com", "password", "nickname", Role.USER, UserType.GENERAL, false, new ArrayList<>());
        authDetails = new AuthDetails(user);
    }

    @DisplayName("식별자를 통해 에피소드를 가져올 수 있다")
    @Test
    void getEpisode() throws Exception {
        // given
        given(bookService.getEpisode(1L, 1L, 1L)).willReturn(new EpisodeResponse(1L, "title", "content"));

        // when & then
        mvc.perform(get("/api/v1/books/1/episodes/1").with(user(authDetails)))
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
        final Book givenBook = new Book(1L, "title", 1L, 1L, BookStatus.SELLING, List.of(new Episode(1L, "title", "content", 1, BookStatus.REGISTERED, 10)));
        given(bookService.addEpisode(1L, "title", "content", 1, 10)).willReturn(givenBook);

        // when & then
        mvc.perform(post("/api/v1/books/1/episodes")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"title\", \"content\":\"content\"}"))
                .andExpect(status().isCreated());
    }
}
