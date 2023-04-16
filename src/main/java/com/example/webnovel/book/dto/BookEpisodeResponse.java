package com.example.webnovel.book.dto;

import com.example.webnovel.book.domain.book.Episode;
import com.example.webnovel.global.utils.TimeUtil;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

/**
 * 도서 조회시 같이 보여주는 에피소드 정보.
 */
@Getter
public class BookEpisodeResponse {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    private Long episodeId;
    private String title;
    private Integer page;
    private String createdDate;

    public BookEpisodeResponse() {
    }

    public BookEpisodeResponse(Long episodeId, String title, Integer page, String createdDate) {
        this.episodeId = episodeId;
        this.title = title;
        this.page = page;
        this.createdDate = createdDate;
    }

    public BookEpisodeResponse(Episode episode) {
        this.episodeId = episode.getId();
        this.title = episode.getTitle();
        this.page = episode.getPage();
        this.createdDate = TimeUtil.convertStringLocalTimeToDate(episode.getCreateTime());
    }
}
