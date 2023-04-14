package com.example.webnovel.user.application;

import com.example.webnovel.user.domain.User;
import com.example.webnovel.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
}
