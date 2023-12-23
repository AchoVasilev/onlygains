package com.project.application.models.post

import com.project.domain.post.Post
import io.micronaut.serde.annotation.Serdeable
import java.time.ZonedDateTime
import java.util.UUID

@Serdeable
data class PostViewResource(val id: UUID, val imageUrl: String?, val createdAt: ZonedDateTime, val createdBy: String,
                       val creatorImageUrl: String?, val title: String?, val text: String?, val previewText: String?, val categoryName: String?,
                       val categoryNameTranslation: String?, val categoryId: UUID) {
    companion object {
        fun from(post: Post): PostViewResource {
            return PostViewResource(post.id,
                    post.postImages[0].url,
                    post.createdAt,
                    post.user!!.getFullName(), post.user!!.imageUrl,
                    post.title, post.text, post.previewText, post.category!!.name,
                    post.category!!.translatedName, post.category!!.id)
        }
    }
}
