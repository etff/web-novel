package com.example.webnovel.book.domain.book;

import lombok.Getter;

import java.util.Objects;

@Getter
public class EpisodeSubscribeEvent {

    private final Long episodeId;
    private final Long userId;
    private final Integer count;

    public EpisodeSubscribeEvent(Long episodeId, Long userId, Integer count) {
        this.episodeId = episodeId;
        this.userId = userId;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EpisodeSubscribeEvent)) return false;
        EpisodeSubscribeEvent that = (EpisodeSubscribeEvent) o;
        return Objects.equals(getEpisodeId(), that.getEpisodeId()) && Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getCount(), that.getCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEpisodeId(), getUserId(), getCount());
    }

    @Override
    public String toString() {
        return "EpisodeSubscribeEvent{" +
                "episodeId=" + episodeId +
                ", userId=" + userId +
                ", count=" + count +
                '}';
    }
}
