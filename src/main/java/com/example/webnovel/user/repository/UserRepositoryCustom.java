package com.example.webnovel.user.repository;

import com.example.webnovel.user.dto.UserFavoriteResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {
    Page<UserFavoriteResponse> findUserFavoriteEpisodes(Long userId, Pageable pageable);
}
