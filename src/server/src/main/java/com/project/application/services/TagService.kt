package com.project.application.services

import com.project.application.models.tag.TagViewResource
import com.project.domain.post.Tag
import com.project.infrastructure.data.TagRepository
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
open class TagService(private val tagRepository: TagRepository) {
    @Transactional(readOnly = true)
    open fun getTags(): List<TagViewResource> {
         return tagRepository.findAll()
                .map { t: Tag -> TagViewResource(t.id, t.name, t.translatedName) }
    }

    @Transactional(readOnly = true)
    open fun getTags(tagIds: List<UUID>): List<Tag> {
        return tagRepository.findByIdIn(tagIds)
    }
}
