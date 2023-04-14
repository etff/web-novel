package com.example.webnovel.auth.application;

import com.example.webnovel.auth.dto.AuthDetails;
import com.example.webnovel.auth.util.TokenManager;
import com.example.webnovel.global.error.exception.BusinessException;
import com.example.webnovel.global.error.exception.EntityNotFoundException;
import com.example.webnovel.global.error.exception.ErrorCode;
import com.example.webnovel.user.domain.User;
import com.example.webnovel.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Component
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenManager tokenManager;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(email));
        return new AuthDetails(user);
    }

    @Transactional(readOnly = true)
    public String getToken(String email, String password) {
        final UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);
        final User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(email));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BusinessException("invalid password", ErrorCode.HANDLE_ACCESS_DENIED);
        }

        final Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenManager.createToken(authentication);
    }

}
