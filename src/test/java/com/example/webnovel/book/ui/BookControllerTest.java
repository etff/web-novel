package com.example.webnovel.book.ui;

import com.example.webnovel.book.application.BookService;
import com.example.webnovel.book.domain.book.Book;
import com.example.webnovel.book.domain.book.Episode;
import com.example.webnovel.book.domain.book.type.BookStatus;
import com.example.webnovel.book.dto.BookResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @DisplayName("도서를 생성할 수 있다")
    @Test
    void create() throws Exception {
        // given
        given(bookService.createBook("title", 1L, 1L)).willReturn(1L);

        // when & then
        mvc.perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"title\", \"authorId\":1, \"categoryId\":1}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/books/1"));
    }

    @DisplayName("식별자를 통해 도서를 가져올 수 있다")
    @Test
    void getBookById() throws Exception {
        // given
        given(bookService.getBook(1L)).willReturn(new BookResponse(1L, "title", 1L, 1L));

        // when & then
        mvc.perform(get("/api/v1/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("bookId").value(1L))
                .andExpect(jsonPath("title").value("title"))
                .andExpect(jsonPath("authorId").value(1L))
                .andExpect(jsonPath("categoryId").value(1L));
    }

    @DisplayName("도서 상태와 페이지 정보를 통해 도서 목록을 가져올 수 있다")
    @Test
    void getBooksWithPageInfo() throws Exception {
        // given
        given(bookService.getBooks(BookStatus.SALE, PageRequest.of(1, 10))).willReturn(
                new PageImpl<>(List.of(
                        new BookResponse(1L, "title1", 1L, 1L),
                        new BookResponse(2L, "title2", 1L, 1L)
                ))
        );

        // when & then
        mvc.perform(get("/api/v1/books")
                        .param("bookStatus", "SALE")
                        .param("page", "1")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("content[0].bookId").value(1L))
                .andExpect(jsonPath("content[0].title").value("title1"))
                .andExpect(jsonPath("content[0].authorId").value(1L))
                .andExpect(jsonPath("content[0].categoryId").value(1L));
    }

    @DisplayName("식별자를 통해 에피소드를 가져올 수 있다")
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
    @Test
    void addEpisode() throws Exception {
        // given
        final Book givenBook = new Book(1L, "title", 1L, 1L, new Episode(1L, "title", "content", 1));
        given(bookService.addEpisode(1L, "title", "content", 1)).willReturn(givenBook);

        // when & then
        mvc.perform(post("/api/v1/books/1/episodes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"title\", \"content\":\"content\"}"))
                .andExpect(status().isCreated());
    }
}
