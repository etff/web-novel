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
    @PositiveOrZero(message = "페이지는 0 이상이어야 합니다.")
    private Integer page;

    public EpisodeCreateRequest(String title, String content, Integer ticketPrice, Integer page) {
        this.title = title;
        this.content = content;
        this.ticketPrice = ticketPrice;
        this.page = page;
    }
}
