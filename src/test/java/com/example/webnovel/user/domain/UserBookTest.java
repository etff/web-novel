package com.example.webnovel.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserBookTest {

    @DisplayName("UserBook 생성 테스트")
    @Test
    void create() {
        User user = User.ofUser("test@test.com", "test", "pass");
        UserBook userBook = new UserBook(user, 1L, 0);
        assertNotNull(userBook);
    }

    @DisplayName("페이지를 변경하면 페이지가 변경된다.")
    @Test
    void change_page() {
        User user = User.ofUser("test@test.com", "test", "pass");
        UserBook userBook = new UserBook(user, 1L, 0);

        userBook.changePage(1);

        assertThat(userBook.getPage()).isEqualTo(1);
    }
}
