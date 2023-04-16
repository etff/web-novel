package com.example.webnovel.book.domain.book;

import com.example.webnovel.book.domain.book.type.BookStatus;
import com.example.webnovel.global.error.exception.EntityNotFoundException;
import com.example.webnovel.global.error.exception.InvalidValueException;
import com.example.webnovel.global.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 도서.
 */
@Entity
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_status")
    private BookStatus bookStatus;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "author_id")
    private Long authorId;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Episode> episodes = new ArrayList<>();

    protected Book() {
    }

    public Book(String title) {
        this(null, title, null, null, BookStatus.REGISTERED, null);
    }

    public Book(String title, Long categoryId, Long authorId) {
        this(null, title, categoryId, authorId, BookStatus.REGISTERED, null);
    }

    public Book(Long id, String title, Long categoryId, Long authorId, BookStatus bookStatus, List<Episode> episodes) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("제목이 비어있으면 안됩니다.");
        }
        this.id = id;
        this.title = title;
        this.categoryId = categoryId;
        this.authorId = authorId;
        this.bookStatus = bookStatus;
        this.episodes = episodes == null ? new ArrayList<>() : episodes;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public List<Episode> getEpisodes() {
        return Collections.unmodifiableList(episodes);
    }

    public void addEpisode(Episode episode) {
        this.episodes.add(episode);
        episode.setBook(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(getId(), book.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public Episode getEpisode(Long episodeId) {
        return episodes.stream()
                .filter(episode -> episode.isSameEpisodeId(episodeId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("해당 에피소드가 없습니다."));
    }

    public void updateStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public void onSaleCheck() {
        if (this.bookStatus != BookStatus.SELLING) {
            throw new InvalidValueException("판매중인 도서가 아닙니다.");
        }
    }
}
