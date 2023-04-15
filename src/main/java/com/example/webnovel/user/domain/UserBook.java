package com.example.webnovel.user.domain;

import com.example.webnovel.global.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자 책. 사용자가 가진 책의 정보를 가진다.
 */
@Getter
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"episode_id", "user_id"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBook extends BaseEntity {

    @Id
    @Column(name = "user_book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "episode_id")
    private Long episodeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Integer page;

    public UserBook(User user, Long episodeId, Integer page) {
        this(null, user, episodeId, page);
    }

    public UserBook(Long id, User user, Long episodeId, Integer page) {
        this.id = id;
        this.episodeId = episodeId;
        this.user = user;
        this.page = page;
    }

    public void changePage(Integer page) {
        this.page = page;
    }
}
