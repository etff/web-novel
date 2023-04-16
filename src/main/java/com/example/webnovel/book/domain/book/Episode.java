package com.example.webnovel.book.domain.book;

import com.example.webnovel.book.domain.book.type.BookStatus;
import com.example.webnovel.global.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

import static jakarta.persistence.FetchType.LAZY;

@Entity
public class Episode extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * 티켓 가격. 회차를 구매할 때 사용.
     */
    private Integer ticketPrice;

    private Integer page;

    protected Episode() {
    }

    public Episode(String title) {
        this(title, null);
    }

    public Episode(String title, String content) {
        this(null, title, content, null, null);
    }

    public Episode(String title, String content, Integer ticketPrice) {
        this(null, title, content, ticketPrice, null);
    }

    public Episode(Long id, String title, String content, Integer ticketPrice, Integer page) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("제목이 비어있으면 안됩니다.");
        }
        this.id = id;
        this.title = title;
        this.content = content;
        this.ticketPrice = ticketPrice;
        this.bookStatus = BookStatus.REGISTERED;
        this.page = page;
    }

    public Long getId() {
        return id;
    }

    public boolean isSameEpisodeId(Long episodeId) {
        return Objects.equals(this.id, episodeId);
    }

    public String getTitle() {
        return title;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public String getContent() {
        return content;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getPage() {
        return page;
    }
}
