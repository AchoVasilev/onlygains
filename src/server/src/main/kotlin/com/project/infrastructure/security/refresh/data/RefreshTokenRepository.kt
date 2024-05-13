package com.project.infrastructure.security.refresh.data

import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.time.Instant
import java.util.UUID

@Repository
interface RefreshTokenRepository : CrudRepository<RefreshToken, UUID> {
    fun findByToken(token: String): RefreshToken?

    @Query(value = "UPDATE RefreshToken rt SET rt.isRevoked = TRUE, rt.modifiedAt = :now WHERE rt.userId = :userId AND rt.isRevoked = FALSE")
    fun revokeAllByUserId(userId: UUID, now: Instant): Int
}