package com.example.webnovel.book.ui;

import com.example.webnovel.book.application.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @Test
    void create() throws Exception {
        // given
        given(bookService.createBook("title", 1L, 1L)).willReturn(1L);

        // when & then
        mvc.perform(post("/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"title\", \"authorId\":1, \"categoryId\":1}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/books/1"));
    }
}
