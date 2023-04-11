package com.example.webnovel.book.dto;

import com.example.webnovel.book.domain.book.Book;
import lombok.Getter;

@Getter
public class BookResponse {
    private Long bookId;
    private String title;
    private Long authorId;
    private Long categoryId;

    public BookResponse() {
    }

    public BookResponse(Long bookId, String title, Long authorId, Long categoryId) {
        this.bookId = bookId;
        this.title = title;
        this.authorId = authorId;
        this.categoryId = categoryId;
    }

    public BookResponse(Book book) {
        this.bookId = book.getId();
        this.title = book.getTitle();
    }
}
