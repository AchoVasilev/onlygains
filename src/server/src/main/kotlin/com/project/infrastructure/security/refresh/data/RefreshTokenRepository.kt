package com.project.infrastructure.security.refresh.data

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.UUID

@Repository
interface RefreshTokenRepository : CrudRepository<RefreshToken, UUID> {
    fun findByToken(token: String): RefreshToken?
}