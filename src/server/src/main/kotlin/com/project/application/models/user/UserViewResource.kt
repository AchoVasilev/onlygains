package com.project.application.models.user

import com.project.domain.user.User
import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
data class UserViewResource(val id: UUID, val fullName: String, val email: String?) {
    companion object {
        fun from(user: User): UserViewResource {
            return UserViewResource(user.id, user.getFullName(), user.email)
        }
    }
}
