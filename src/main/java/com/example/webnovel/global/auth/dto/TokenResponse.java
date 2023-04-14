package com.example.webnovel.global.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TokenResponse {
    private String token;

    public TokenResponse(String token) {
        this.token = token;
    }
}
