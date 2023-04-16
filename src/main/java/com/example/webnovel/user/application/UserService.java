package com.example.webnovel.user.application;

import com.example.webnovel.user.domain.User;
import com.example.webnovel.user.dto.UserFavoriteResponse;
import com.example.webnovel.user.exception.EmailDuplicatedException;
import com.example.webnovel.user.exception.UserNotFoundException;
import com.example.webnovel.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long registerUser(String email, String name, String password) {
        userRepository.findByEmail(email)
                .ifPresent(user -> {
                    throw new EmailDuplicatedException(email);
                });
        final User user = User.ofUser(email, name, passwordEncoder.encode(password));
        final User saved = userRepository.save(user);
        return saved.getId();
    }

    public User subscribeEpisode(Long userId, Long episodeId, Integer count) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + userId));

        user.subscribeEpisode(episodeId, count);
        return user;
    }

    @Transactional(readOnly = true)
    public Page<UserFavoriteResponse> getUserFavoriteEpisodes(Long userId, Pageable pageable) {
        return userRepository.findUserFavoriteEpisodes(userId, pageable);
    }

    public void increaseUserTicket(Long userId, Integer count) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + userId));

        user.increaseTicket(count);
    }
}
