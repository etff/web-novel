package com.example.webnovel.book.ui;

import com.example.webnovel.book.domain.book.Episode;
import lombok.Getter;

@Getter
public class EpisodeResponse {
    private Long episodeId;
    private String title;
    private String content;

    public EpisodeResponse() {
    }

    public EpisodeResponse(Long episodeId, String title, String content) {
        this.episodeId = episodeId;
        this.title = title;
        this.content = content;
    }

    public EpisodeResponse(Episode episode) {
        this.episodeId = episode.getEpisodeId();
        this.title = episode.getTitle();
        this.content = episode.getContent();
    }
}
