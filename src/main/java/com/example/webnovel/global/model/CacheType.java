package com.example.webnovel.global.model;

import lombok.Getter;

@Getter
public enum CacheType {
    CACHE_STORE("bookResponse", 5 * 60, 10000);

    private final String cacheName;     // 캐시 이름
    private final int expireAfterWrite; // 만료시간
    private final int maximumSize;      // 최대 갯수

    CacheType(String cacheName, int expireAfterWrite, int maximumSize) {
        this.cacheName = cacheName;
        this.expireAfterWrite = expireAfterWrite;
        this.maximumSize = maximumSize;
    }
}
