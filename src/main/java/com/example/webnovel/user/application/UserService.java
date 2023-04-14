package com.example.webnovel.user.application;

import com.example.webnovel.user.domain.User;
import com.example.webnovel.user.exception.EmailDuplicatedException;
import com.example.webnovel.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
