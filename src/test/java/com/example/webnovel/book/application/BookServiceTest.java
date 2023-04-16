package com.example.webnovel.book.application;

import com.example.webnovel.book.domain.book.Book;
import com.example.webnovel.book.domain.book.Episode;
import com.example.webnovel.book.domain.book.type.BookStatus;
import com.example.webnovel.book.dto.BookResponse;
import com.example.webnovel.book.dto.EpisodeResponse;
import com.example.webnovel.book.infra.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
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

    @Mock
    private ApplicationEventPublisher eventPublisher;

    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService(bookRepository, eventPublisher);
    }

    @DisplayName("책을 생성할 수 있다.")
    @Test
    void createBook() {
        // given
        given(bookRepository.save(any(Book.class))).willReturn(new Book(1L, "title", 1L, 1L, null));
        // when
        final Long actual = bookService.createBook("title", 1L, 1L);
        // then
        assertThat(actual).isEqualTo(1L);
    }

    @DisplayName("책을 조회할 수 있다.")
    @Test
    void readBookById() {
        // given
        final Book givenBook = new Book(1L, "title", 1L, 1L, null);
        given(bookRepository.findById(anyLong())).willReturn(Optional.of(givenBook));
        // when
        final BookResponse actual = bookService.getBook(1L);

        assertThat(actual.getBookId()).isEqualTo(givenBook.getId());
    }

    @DisplayName("책을 페이지 단위로 조회할 수 있다.")
    @Test
    void readBookByPage() {
        // given
        given(bookRepository.findAllByBookStatus(any(), any())).willReturn(
                new PageImpl<>(List.of(new Book(1L, "title", 1L, 1L, null)))
        );

        // when
        Page<BookResponse> actual = bookService.getBooks(BookStatus.SELLING, PageRequest.of(1, 10));

        // then
        assertAll(
                () -> assertThat(actual.getTotalPages()).isEqualTo(1),
                () -> assertThat(actual.getTotalElements()).isEqualTo(1),
                () -> assertThat(actual.getContent().get(0).getBookId()).isEqualTo(1L)
        );
    }

    @DisplayName("에피소드를 조회할 수 있다.")
    @Test
    void getEpisode() {
        // given
        final Book givenBook = new Book(1L, "title", 1L, 1L, BookStatus.SELLING, new Episode(1L, "title", "content", 0, BookStatus.SELLING));
        given(bookRepository.findById(anyLong())).willReturn(Optional.of(givenBook));

        // when
        EpisodeResponse actual = bookService.getEpisode(1L, 1L, 1L);

        // then
        assertThat(actual.getEpisodeId()).isEqualTo(1L);
    }

    @DisplayName("에피소드를 추가할 수 있다.")
    @Test
    void addEpisode() {
        // given
        final Book givenBook = new Book(1L, "title", 1L, 1L, BookStatus.SELLING, null);
        given(bookRepository.findById(anyLong())).willReturn(Optional.of(givenBook));

        // when
        Book actual = bookService.addEpisode(1L, "title", "content", 1);

        // then
        assertThat(actual.getEpisodes().size()).isEqualTo(1);
    }

    @Test
    void updateBook() {
        // given
        final Book givenBook = new Book(1L, "title", 1L, 1L, null);
        given(bookRepository.findById(anyLong())).willReturn(Optional.of(givenBook));

        // when
        Book actual = bookService.updateStatus(1L, BookStatus.SALE_END);

        // then
        assertThat(actual.getBookStatus()).isEqualTo(BookStatus.SALE_END);
    }
}
