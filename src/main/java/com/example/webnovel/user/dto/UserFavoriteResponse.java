package com.example.webnovel.user.dto;

import com.example.webnovel.global.utils.TimeUtil;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserFavoriteResponse {
    private Long episodeId;
    private String title;
    private Integer episodePage;
    private Integer userPage;
    private String lastViewDate;

    @QueryProjection
    public UserFavoriteResponse(Long episodeId, String title, Integer episodePage, Integer userPage, String createdTime) {
        this.episodeId = episodeId;
        this.title = title;
        this.episodePage = episodePage;
        this.userPage = userPage;
        this.lastViewDate = TimeUtil.convertStringLocalTimeToDate(createdTime);
    }
}
