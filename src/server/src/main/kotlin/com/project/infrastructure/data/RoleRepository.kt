package com.project.infrastructure.data

import com.project.domain.user.Role
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.UUID

@Repository
interface RoleRepository : CrudRepository<Role, UUID>
