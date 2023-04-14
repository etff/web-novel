package com.example.webnovel.global.config.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class LoginAuditAware implements AuditorAware<String> {
    private final HttpServletRequest httpServletRequest;

    @Override
    public Optional<String> getCurrentAuditor() {
        final Optional<String> userEmail = Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Principal::getName);
        if (userEmail.isPresent()) {
            return userEmail;
        }
        return Optional.ofNullable(httpServletRequest.getRequestURI());
    }
}
