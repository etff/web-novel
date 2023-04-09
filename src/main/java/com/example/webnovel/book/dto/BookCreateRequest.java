package com.example.webnovel.book.dto;

import com.example.webnovel.book.domain.book.Book;
import lombok.Getter;

@Getter
public class BookCreateRequest {
    private String title;
    private Long categoryId;
    private Long authorId;

    public BookCreateRequest() {
    }

    public BookCreateRequest(String title, Long categoryId, Long authorId) {
        this.title = title;
        this.categoryId = categoryId;
        this.authorId = authorId;
    }

    public Book toEntity() {
        return new Book(title, categoryId, authorId);
    }
}
