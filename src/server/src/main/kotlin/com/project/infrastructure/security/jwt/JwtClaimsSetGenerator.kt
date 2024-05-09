package com.project.infrastructure.security.jwt

import com.nimbusds.jwt.JWTClaimsSet
import io.micronaut.context.annotation.Value
import io.micronaut.security.authentication.Authentication
import jakarta.inject.Singleton
import java.time.Instant
import java.util.Date

@Singleton
class JwtClaimsSetGenerator(@Value("\${jwt.issuer}") private val issuer: String) {

    fun generateClaims(authentication: Authentication, expiration: Long): JWTClaimsSet {
        val now = Instant.now()

        val claimsBuilder = JWTClaimsSet.Builder()
            .issuer(this.issuer)
            .issueTime(Date())
            .expirationTime(Date(now.plusSeconds(expiration).toEpochMilli()))
            .subject(authentication.name)
            .claim("roles", authentication.roles)

        return claimsBuilder.build()
    }
}