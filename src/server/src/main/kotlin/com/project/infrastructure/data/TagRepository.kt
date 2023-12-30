package com.project.infrastructure.data

import com.project.domain.post.Tag
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.UUID

@Repository
interface TagRepository : JpaRepository<Tag, UUID> {
    fun findByIdIn(ids: List<UUID>): List<Tag>
}
