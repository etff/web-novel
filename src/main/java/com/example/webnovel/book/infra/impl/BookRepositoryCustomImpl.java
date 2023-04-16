package com.example.webnovel.book.infra.impl;

import com.example.webnovel.book.infra.BookRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.example.webnovel.book.domain.book.QBook.book;
import static com.example.webnovel.book.domain.book.QEpisode.episode;
import static com.example.webnovel.user.domain.QUserBook.userBook;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {
    private JPAQueryFactory queryFactory;

    @Override
    public List<Long> getBestSellerBookIds() {
        return queryFactory.select(
                        book.id
                ).from(userBook)
                .join(episode).on(episode.id.eq(userBook.episodeId))
                .join(book).on(book.eq(episode.book))
                .where(userBook.page.gt(0)
                        .and(userBook.updateTime.between(
                                LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00")),
                                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd 23:59:59"))))
                )
                .groupBy(userBook.episodeId)
                .orderBy(book.id.desc())
                .limit(10)
                .fetch();
    }
}
