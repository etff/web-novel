package com.example.webnovel.book.application;

import com.example.webnovel.book.domain.book.Book;
import com.example.webnovel.book.domain.book.Episode;
import com.example.webnovel.book.domain.book.type.BookStatus;
import com.example.webnovel.book.dto.BookResponse;
import com.example.webnovel.book.dto.EpisodeResponse;
import com.example.webnovel.book.infra.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class BookService {
    public static final String NOT_EXIST_BOOK = "존재하지 않는 책입니다.";
    private final BookRepository bookRepository;

    public Long createBook(String title, Long authorId, Long categoryId) {
        Book entity = new Book(title, authorId, categoryId);
        final Book saved = bookRepository.save(entity);
        return saved.getBookId();
    }

    @Transactional(readOnly = true)
    public BookResponse getBook(Long bookId) {
        final Book book = findBook(bookId);
        return new BookResponse(book);
    }

    @Transactional(readOnly = true)
    public Page<BookResponse> getBooks(BookStatus bookStatus, Pageable pageable) {
        return bookRepository.findAllByBookStatus(bookStatus, pageable)
                .map(BookResponse::new);
    }

    @Transactional(readOnly = true)
    public EpisodeResponse getEpisode(Long bookId, Long episodeId) {
        final Book book = findBook(bookId);
        return new EpisodeResponse(book.getEpisode(episodeId));
    }

    public Book addEpisode(Long bookId, String title, String content, Integer ticketPrice) {
        final Book book = findBook(bookId);
        book.addEpisode(new Episode(title, content, ticketPrice));
        return book;
    }

    private Book findBook(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_BOOK));
    }
}
