package com.project.utilities;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Time {
    private Time() {
    }

    public static ZonedDateTime utcNow() {
        return ZonedDateTime.now(ZoneId.of("UTC"));
    }
}
