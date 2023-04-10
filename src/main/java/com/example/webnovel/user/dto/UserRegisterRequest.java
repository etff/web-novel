package com.example.webnovel.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserRegisterRequest {
    @Email
    private String email;
    @NotBlank
    private String password;
    private String name;

    public UserRegisterRequest() {
    }

    public UserRegisterRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }


}
