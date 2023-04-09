package com.example.webnovel.book.application;

import com.example.webnovel.book.domain.book.Book;
import com.example.webnovel.book.dto.BookResponse;
import com.example.webnovel.book.infra.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class BookService {
    public static final String NOT_EXIST_BOOK = "존재하지 않는 책입니다.";
    private final BookRepository bookRepository;

    public Long createBook(String title, Long authorId, Long categoryId) {
        final Book saved = bookRepository.save(new Book(title, authorId, categoryId));
        return saved.getBookId();
    }

    @Transactional(readOnly = true)
    public BookResponse getBook(Long bookId) {
        final Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_BOOK));
        return new BookResponse(book);
    }
}
