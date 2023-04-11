package com.example.webnovel.user.domain;

import com.example.webnovel.user.domain.type.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class UserTest {

    @DisplayName("사용자를 생성할 수 있다")
    @Test
    void create_user() {
        // given
        final String givenEmail = "test@test.com";
        final String givenName = "test";
        final String givenPassword = "test";

        // when
        final User user = User.ofUser(givenEmail, givenName, givenPassword);

        // then
        assertAll(
                () -> assertThat(user.getEmail()).isEqualTo(givenEmail),
                () -> assertThat(user.getRole()).isEqualTo(Role.USER)
        );
    }

    @DisplayName("관리자를 생성할 수 있다")
    @Test
    void create_admin() {
        // given
        final String givenEmail = "test@test.com";
        final String givenName = "test";
        final String givenPassword = "test";

        // when
        final User user = User.ofAdmin(givenEmail, givenName, givenPassword);

        // then
        assertAll(
                () -> assertThat(user.getEmail()).isEqualTo(givenEmail),
                () -> assertThat(user.getRole()).isEqualTo(Role.ADMIN)
        );
    }
}
