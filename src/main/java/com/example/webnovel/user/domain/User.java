package com.example.webnovel.user.domain;

import com.example.webnovel.global.model.BaseEntity;
import com.example.webnovel.user.domain.type.Role;
import com.example.webnovel.user.domain.type.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Getter
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Getter
    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Getter
    @JsonIgnore
    @Column(name = "password", length = 200, nullable = false)
    private String password;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false)
    private Role role;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", length = 10, nullable = false)
    private UserType userType;

    private boolean deleted;

    @Builder
    private User(Long id, String email, String name, String password, Role role, UserType userType) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
        this.userType = userType;
        this.deleted = false;
    }

    public static User ofUser(String email, String name, String password) {
        return User.builder()
                .email(email)
                .name(name)
                .password(password)
                .role(Role.USER)
                .userType(UserType.GENERAL)
                .build();
    }

    public static User ofAdmin(String email, String name, String password) {
        return User.builder()
                .email(email)
                .name(name)
                .password(password)
                .role(Role.ADMIN)
                .userType(UserType.GENERAL)
                .build();
    }

    public void changePassword(String password) {
        this.password = password;
    }
}
