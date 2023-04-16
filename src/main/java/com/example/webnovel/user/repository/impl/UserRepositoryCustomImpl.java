package com.example.webnovel.user.repository.impl;

import com.example.webnovel.user.dto.QUserFavoriteResponse;
import com.example.webnovel.user.dto.UserFavoriteResponse;
import com.example.webnovel.user.repository.UserRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.webnovel.book.domain.book.QEpisode.episode;
import static com.example.webnovel.user.domain.QUserBook.userBook;

public record UserRepositoryCustomImpl(JPAQueryFactory queryFactory) implements UserRepositoryCustom {
    @Override
    public Page<UserFavoriteResponse> findUserFavoriteEpisodes(Long userId, Pageable pageable) {
        List<UserFavoriteResponse> userFavoriteResponses = getUserFavorites(userId, pageable);

        int totalCount = Math.toIntExact(queryFactory.select(
                        userBook.count())
                .from(userBook)
                .join(episode).on(episode.id.eq(userBook.episodeId))
                .where(userBook.user.id.eq(userId)
                        .and(userBook.page.gt(0))
                ).fetchFirst());
        return new PageImpl<>(userFavoriteResponses, pageable, totalCount);
    }

    private List<UserFavoriteResponse> getUserFavorites(Long userId, Pageable pageable) {
        return queryFactory.select(
                        new QUserFavoriteResponse(
                                userBook.episodeId,
                                episode.title,
                                episode.page,
                                userBook.page,
                                userBook.updateTime))
                .from(userBook)
                .join(episode).on(episode.id.eq(userBook.episodeId))
                .where(userBook.user.id.eq(userId)
                        .and(userBook.page.gt(0))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(userBook.updateTime.desc())
                .fetch();
    }
}
