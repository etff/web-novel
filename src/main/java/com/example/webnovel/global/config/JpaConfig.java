package com.example.webnovel.global.config;

import jakarta.persistence.MappedSuperclass;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@MappedSuperclass
@EnableJpaAuditing
@Configuration
public class JpaConfig {
}
