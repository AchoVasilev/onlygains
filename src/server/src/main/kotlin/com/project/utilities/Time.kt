package com.project.utilities

import java.time.ZoneId
import java.time.ZonedDateTime

object Time {
    @JvmStatic
    fun utcNow(): ZonedDateTime {
        return ZonedDateTime.now(ZoneId.of("UTC"))
    }
}
