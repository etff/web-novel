package com.example.webnovel.book.domain.book;

import com.example.webnovel.book.domain.book.type.BookStatus;
import com.example.webnovel.global.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import static jakarta.persistence.FetchType.LAZY;

@Entity
public class Episode extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chapterId;
    private String title;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    protected Episode() {
    }

    public Episode(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("제목이 비어있으면 안됩니다.");
        }
        this.title = title;
        this.bookStatus = BookStatus.REGISTERED;
    }

    public String getTitle() {
        return title;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }
}
