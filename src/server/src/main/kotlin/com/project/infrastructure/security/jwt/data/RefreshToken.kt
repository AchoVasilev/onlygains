package com.project.infrastructure.security.jwt.data

import com.project.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.Instant
import java.util.UUID


@Entity
class RefreshToken protected constructor(): BaseEntity() {
    @Id
    val id: UUID = UUID.randomUUID()
    var token: String = ""
    var userId: UUID? = null
    var expiryDate: Instant? = null

    constructor(refreshToken: String, userId: UUID, expiryDate: Instant = Instant.now()) : this() {
        this.token = refreshToken
        this.userId = userId
        this.expiryDate = expiryDate
    }
}