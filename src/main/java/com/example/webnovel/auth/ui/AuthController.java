package com.example.webnovel.auth.ui;

import com.example.webnovel.auth.application.AuthService;
import com.example.webnovel.auth.dto.LoginRequest;
import com.example.webnovel.auth.dto.TokenResponse;
import com.example.webnovel.global.config.security.filter.JwtFilter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private static final String BEARER = "Bearer ";
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authorize(@Valid @RequestBody LoginRequest loginRequest) {
        final String token = authService.getToken(loginRequest.getEmail(), loginRequest.getPassword());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, BEARER + token);

        return new ResponseEntity<>(new TokenResponse(token), httpHeaders, HttpStatus.OK);
    }
}
