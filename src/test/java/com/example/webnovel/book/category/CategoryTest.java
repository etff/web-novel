package com.example.webnovel.book.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class CategoryTest {

    @DisplayName("카테고리 제목을 입력받아 카테고리를 생성할 수 있다")
    @Test
    void create() {
        String givenTitle = "test";
        Category actual = new Category(givenTitle);

        assertThat(actual.getTitle()).isEqualTo(givenTitle);
    }

    @DisplayName("카테고리 제목이 비어있으면 예외를 던진다.")
    @ParameterizedTest
    @NullAndEmptySource
    void create_with_not_exist_title(String givenTitle) {

        assertThatCode(() -> new Category(givenTitle))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
