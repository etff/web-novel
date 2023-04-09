package com.example.webnovel.book.application;

import com.example.webnovel.book.domain.book.Book;
import com.example.webnovel.book.infra.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;

    public Long createBook(String title, Long authorId, Long categoryId) {
        final Book saved = bookRepository.save(new Book(title, authorId, categoryId));
        return saved.getBookId();
    }
}
