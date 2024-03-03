package com.project.infrastructure.data

import com.project.domain.user.User
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.UUID

@Repository
interface UserRepository : CrudRepository<User, UUID> {
    fun findAllByIdIn(ids: List<UUID?>): List<User>
}
