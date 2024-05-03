package com.project.infrastructure.utilities

import java.time.Instant
import java.time.ZoneId

object Time {
    @JvmStatic
    fun utcNow(): Instant {
        return Instant.now()
    }

    @JvmStatic
    val utcZone = ZoneId.of("UTC")
}
