package com.example.webnovel.book.domain.book;

import com.example.webnovel.book.domain.book.type.BookStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class BookTest {

    @DisplayName("도서를 생성할 수 있다")
    @Test
    void create() {
        String givenTitle = "test";
        Book actual = new Book(givenTitle);

        assertThat(actual.getTitle()).isEqualTo(givenTitle);
        assertThat(actual.getBookStatus()).isEqualTo(BookStatus.REGISTERED);
    }

    @DisplayName("도서 제목이 비어있으면 예외를 던진다.")
    @ParameterizedTest
    @NullAndEmptySource
    void create_with_not_exist_title(String givenTitle) {

        assertThatCode(() -> new Book(givenTitle))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void addEpisode() {
        // given
        final Book book = new Book("title");
        final Episode episode = new Episode("title", "content", 1, 10);
        // when
        book.addEpisode(episode);
        // then
        assertThat(book.getEpisodes().contains(episode)).isTrue();
    }
}
