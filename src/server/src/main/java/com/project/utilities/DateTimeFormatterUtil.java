package com.project.utilities;

import java.time.format.DateTimeFormatter;

public final class DateTimeFormatterUtil {
    private DateTimeFormatterUtil() {}

    public static DateTimeFormatter formatterFrom(String pattern) {
        return DateTimeFormatter.ofPattern(pattern);
    }
}
