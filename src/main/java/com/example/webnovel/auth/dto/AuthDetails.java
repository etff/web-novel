package com.example.webnovel.auth.dto;

import com.example.webnovel.global.error.exception.EntityNotFoundException;
import com.example.webnovel.user.domain.User;
import com.example.webnovel.user.domain.type.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class AuthDetails implements UserDetails {
    private Long id;
    private String email;
    private Role role;

    private String password;

    public AuthDetails(User user) {
        if (user.isDeleted()) {
            throw new EntityNotFoundException("삭제된 사용자입니다.");
        }
        this.id = user.getId();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.password = user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add(() -> "ROLE_" + this.role.name());
        return collector;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
