package com.krtv.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class DateUtils {

    private static final String PATTERN_OUTPUT = "YYYY-MM-dd HH:mm:ss";

    public static String localDateTimeToStringDate(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return localDateTime.format(DateTimeFormatter.ofPattern(PATTERN_OUTPUT));
    }
}
