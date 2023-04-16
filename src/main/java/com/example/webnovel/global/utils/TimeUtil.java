package com.example.webnovel.global.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    private TimeUtil() {
    }

    public static String convertStringLocalTimeToDate(String time) {
        if (time == null || time.isBlank()) {
            return "";
        }
        LocalDateTime localDateTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return localDateTime.format(formatter);
    }
}
