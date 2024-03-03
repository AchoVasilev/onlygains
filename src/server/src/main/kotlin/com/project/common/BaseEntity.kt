package com.project.common

import com.project.utilities.Time.utcNow
import jakarta.persistence.MappedSuperclass
import java.time.ZonedDateTime

@MappedSuperclass
abstract class BaseEntity protected constructor() {
    val createdAt: ZonedDateTime = utcNow()
    var modifiedAt: ZonedDateTime? = null
    var isDeleted: Boolean = false

    fun markAsUpdated() {
        this.modifiedAt = utcNow()
    }

    fun markAsDeleted() {
        this.isDeleted = true
        this.markAsUpdated()
    }
}
