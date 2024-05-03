package com.project.infrastructure.utilities

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object LoggerProvider {
    @JvmStatic
    fun getLogger(className: Class<*>?): Logger {
        return LoggerFactory.getLogger(className)
    }
}
