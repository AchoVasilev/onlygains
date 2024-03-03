package com.project.posts.application

import com.project.posts.application.models.tag.TagViewResource
import com.project.posts.domain.Tag
import com.project.posts.domain.TagRepository
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
