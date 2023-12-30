package com.project.utilities

import java.time.format.DateTimeFormatter

object DateTimeFormatterUtil {
    @JvmStatic
    fun formatterFrom(pattern: String): DateTimeFormatter {
        return DateTimeFormatter.ofPattern(pattern)
    }
}
