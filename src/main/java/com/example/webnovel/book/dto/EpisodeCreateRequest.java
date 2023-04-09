package com.example.webnovel.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EpisodeCreateRequest {
    @NotBlank(message = "제목은 필수입니다.")
    private String title;
    private String content;
    @PositiveOrZero(message = "티켓 가격은 0 이상이어야 합니다.")
    private Integer ticketPrice;

    public EpisodeCreateRequest(String title, String content, Integer ticketPrice) {
        this.title = title;
        this.content = content;
        this.ticketPrice = ticketPrice;
    }
}
