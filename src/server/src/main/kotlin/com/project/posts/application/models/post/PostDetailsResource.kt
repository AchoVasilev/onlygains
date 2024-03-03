package com.project.posts.application.models.post

import com.project.application.models.user.UserViewResource
import com.project.domain.user.User
import com.project.posts.application.models.category.CategoryViewResource
import com.project.posts.application.models.tag.TagViewResource
import com.project.posts.domain.Comment
import com.project.posts.domain.Post
import com.project.posts.domain.Tag
import io.micronaut.serde.annotation.Serdeable
import java.time.ZonedDateTime
import java.util.UUID

@Serdeable
data class PostDetailsResource(val id: UUID, val createdAt: ZonedDateTime, val createdBy: UserViewResource, val title: String?,
                               val text: String?, val previewText: String?, val imageUrls: List<String?>, val category: CategoryViewResource,
                               val comments: List<CommentViewResource>, val tags: List<TagViewResource>) {
    companion object {
        fun from(post: Post, user: User): PostDetailsResource {
            return PostDetailsResource(post.id, post.createdAt, UserViewResource.from(user), post.title,
                    post.text, post.previewText, post.postImages.map {postImage ->  postImage.url},
                    CategoryViewResource.from(post.category!!),
                    post.comments.map { comment: Comment -> CommentViewResource.from(comment) },
                    post.tags.map { tag: Tag -> TagViewResource.from(tag) })
        }
    }
}
