package com.example.webnovel.book.application;

import com.example.webnovel.book.domain.book.Book;
import com.example.webnovel.book.domain.book.type.BookStatus;
import com.example.webnovel.book.dto.BookResponse;
import com.example.webnovel.book.infra.BookRepository;
import com.example.webnovel.book.ui.EpisodeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService(bookRepository);
    }

    @Test
    void createBook() {
        // given
        given(bookRepository.save(any(Book.class))).willReturn(new Book(1L, "title", 1L, 1L));
        // when
        final Long actual = bookService.createBook("title", 1L, 1L);
        // then
        assertThat(actual).isEqualTo(1L);
    }

    @Test
    void readBookById() {
        // given
        final Book givenBook = new Book(1L, "title", 1L, 1L);
        given(bookRepository.findById(anyLong())).willReturn(Optional.of(givenBook));
        // when
        final BookResponse actual = bookService.getBook(1L);

        assertThat(actual.getBookId()).isEqualTo(givenBook.getBookId());
    }

    @Test
    void readBookByPage() {
        // given
        given(bookRepository.findAllByBookStatus(any(), any())).willReturn(
                new PageImpl<>(List.of(new Book(1L, "title", 1L, 1L)))
        );

        // when
        Page<BookResponse> actual = bookService.getBooks(BookStatus.SALE, PageRequest.of(1, 10));

        // then
        assertAll(
                () -> assertThat(actual.getTotalPages()).isEqualTo(1),
                () -> assertThat(actual.getTotalElements()).isEqualTo(1),
                () -> assertThat(actual.getContent().get(0).getBookId()).isEqualTo(1L)
        );
    }

    @Test
    void getEpisode() {
        // get episode
        // given
        final Book givenBook = new Book(1L, "title", 1L, 1L);
        given(bookRepository.findById(anyLong())).willReturn(Optional.of(givenBook));

        // when
        EpisodeResponse actual = bookService.getEpisode(1L, 1L);


    }

}
