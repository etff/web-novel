package com.example.webnovel.user.domain;

import com.example.webnovel.global.error.exception.EntityNotFoundException;
import com.example.webnovel.global.model.BaseEntity;
import com.example.webnovel.user.domain.type.Role;
import com.example.webnovel.user.domain.type.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

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

    @Getter
    private boolean deleted;

    @Getter
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserTicket userTicket;

    @Builder
    public User(Long id, String email, String name, String password, Role role, UserType userType, boolean deleted) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
        this.userType = userType;
        this.deleted = deleted;
    }

    public static User ofUser(String email, String name, String password) {
        return User.builder()
                .email(email)
                .name(name)
                .password(password)
                .role(Role.USER)
                .userType(UserType.GENERAL)
                .deleted(false)
                .build();
    }

    public static User ofAdmin(String email, String name, String password) {
        return User.builder()
                .email(email)
                .name(name)
                .password(password)
                .role(Role.ADMIN)
                .userType(UserType.GENERAL)
                .deleted(false)
                .build();
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void setUserTicket(UserTicket userTicket) {
        this.userTicket = userTicket;
        userTicket.setUser(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public void subscribeEpisode(Long episodeId, Integer count) {
        if (this.userTicket == null) {
            throw new EntityNotFoundException("사용가능한 티켓이 없습니다.");
        }
        this.userTicket.changeTotalCount(-count);
    }
}
