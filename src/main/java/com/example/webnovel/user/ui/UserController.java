package com.example.webnovel.user.ui;

import com.example.webnovel.user.application.UserService;
import com.example.webnovel.user.dto.UserRegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Long> registerUser(@Valid @RequestBody UserRegisterRequest dto) {
        final Long userId = userService.registerUser(dto.getEmail(), dto.getName(), dto.getPassword());
        return ResponseEntity.created(URI.create("/users/" + userId)).body(userId);
    }
}
