package com.example.webnovel.global.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private static DateTimeFormatter dateAndSecondsFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private TimeUtil() {
    }

    public static String convertStringLocalTimeToDate(String time) {
        if (time == null || time.isBlank()) {
            return "";
        }

        LocalDateTime localDateTime = LocalDateTime.parse(time, dateAndSecondsFormatter);
        return localDateTime.format(dateFormatter);
    }
}
