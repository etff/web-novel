package com.example.webnovel.book.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EpisodeCreateRequest {
    @NotBlank(message = "제목은 필수입니다.")
    private String title;
    private String content;

    public EpisodeCreateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
