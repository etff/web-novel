package com.example.webnovel.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class LoginRequest {

    @Email
    private String email;

    @Size(min = 2, max = 20)
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}