package com.example.webnovel.user.repository;

import com.example.webnovel.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
