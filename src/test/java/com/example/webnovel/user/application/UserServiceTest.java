package com.example.webnovel.user.application;

import com.example.webnovel.global.error.exception.InvalidValueException;
import com.example.webnovel.user.domain.User;
import com.example.webnovel.user.domain.UserTicket;
import com.example.webnovel.user.domain.type.Role;
import com.example.webnovel.user.domain.type.UserType;
import com.example.webnovel.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    void register_user() {
        // given
        final String givenEmail = "test@test.com";
        final String givenName = "test";
        final String givenPassword = "test";
        User user = User.ofUser(givenEmail, givenName, givenPassword);
        ReflectionTestUtils.setField(user, "id", 1L);

        given(userRepository.save(any(User.class)))
                .willReturn(user);

        // when
        final Long actual = userService.registerUser(givenEmail, givenName, givenPassword);

        // then
        assertThat(actual).isEqualTo(1L);
    }

    @DisplayName("회차 구독을 하게되면 티켓이 1개 차감된다.")
    @Test
    void subscribeEpisode() {
        // given
        final Long givenUserId = 1L;
        final Long givenEpisodeId = 1L;
        final Integer givenCount = 1;

        User user = new User(1L, "test@test.com", "name", "password", Role.USER, UserType.GENERAL, false, new ArrayList<>());
        UserTicket userTicket = new UserTicket(1L, 10);
        user.setUserTicket(userTicket);
        given(userRepository.findById(any(Long.class)))
                .willReturn(Optional.of(user));

        // when
        User actual = userService.subscribeEpisode(givenUserId, givenEpisodeId, givenCount);

        assertThat(actual.getUserTicket().getTicketCount()).isEqualTo(9);
    }

    @DisplayName("티켓이 부족하게 되면 예외를 던진다.")
    @Test
    void subscribe_with_insufficient_ticket() {
        // given
        final Long givenUserId = 1L;
        final Long givenEpisodeId = 1L;
        final Integer givenCount = 1;

        User user = new User(1L, "test@test.com", "name", "password", Role.USER, UserType.GENERAL, false, new ArrayList<>());
        UserTicket userTicket = new UserTicket(1L, 0);
        user.setUserTicket(userTicket);
        given(userRepository.findById(any(Long.class)))
                .willReturn(Optional.of(user));

        // when
        assertThatCode(() -> userService.subscribeEpisode(givenUserId, givenEpisodeId, givenCount))
                .isInstanceOf(InvalidValueException.class);
    }
}
