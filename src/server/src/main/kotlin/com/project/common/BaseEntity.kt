package com.project.common

import com.project.utilities.Time.utcNow
import jakarta.persistence.MappedSuperclass
import java.time.Instant

@MappedSuperclass
abstract class BaseEntity protected constructor() {
    var createdAt: Instant = utcNow()
    var modifiedAt: Instant? = null
    var isDeleted: Boolean = false

    fun markAsUpdated() {
        this.modifiedAt = utcNow()
    }

    fun markAsDeleted() {
        this.isDeleted = true
        this.markAsUpdated()
    }
}
