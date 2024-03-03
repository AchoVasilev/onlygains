package com.project.posts.application.models.post

import com.project.domain.user.User
import com.project.posts.domain.Post
import io.micronaut.serde.annotation.Serdeable
import java.time.ZonedDateTime
import java.util.UUID

@Serdeable
data class PostViewResource(val id: UUID, val imageUrl: String?, val createdAt: ZonedDateTime, val createdBy: String,
                       val creatorImageUrl: String?, val title: String?, val text: String?, val previewText: String?, val categoryName: String?,
                       val categoryNameTranslation: String?, val categoryId: UUID) {
    companion object {
        fun from(post: Post, user: User): PostViewResource {
            return PostViewResource(post.id,
                    post.postImages[0].url,
                    post.createdAt,
                    user.getFullName(), user.imageUrl,
                    post.title, post.text, post.previewText, post.category!!.name,
                    post.category!!.translatedName, post.category!!.id)
        }
    }
}
