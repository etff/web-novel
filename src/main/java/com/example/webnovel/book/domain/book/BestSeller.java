package com.example.webnovel.book.domain.book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class BestSeller {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "best_seller_id")
    private Long id;

    private Long bookId;

    private LocalDate createdAt;

    private LocalDate startedAt;

    private LocalDate endAt;

}
