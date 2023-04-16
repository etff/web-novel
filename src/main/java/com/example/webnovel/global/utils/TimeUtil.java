package com.example.webnovel.global.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class TimeUtil {
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private static DateTimeFormatter dateAndSecondsFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private TimeUtil() {
    }

    public static String convertStringLocalTimeToDate(String time) {
        if (time == null || time.isBlank()) {
            return "";
        }

        try {
            LocalDateTime localDateTime = LocalDateTime.parse(time, dateAndSecondsFormatter);
            return localDateTime.format(dateFormatter);
        } catch (Exception e) {
            log.debug("변환중 에러가 발생했습니다. : {}", e.getMessage());
            return "";
        }
    }
}
