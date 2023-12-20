package com.project.application.services

import com.project.application.models.post.CreatePostResource
import com.project.application.models.post.PostDetailsResource
import com.project.application.models.post.PostViewResource
import com.project.common.enums.PostQueryType
import com.project.domain.image.PostImage
import com.project.domain.post.Post
import com.project.domain.post.Tag
import com.project.domain.user.User
import com.project.infrastructure.data.PostRepository
import com.project.infrastructure.data.RoleRepository
import com.project.infrastructure.data.UserRepository
import com.project.infrastructure.exceptions.DuplicateEntryException
import com.project.infrastructure.exceptions.EntityNotFoundException
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import org.slf4j.Logger
import java.util.Optional
import java.util.UUID
import java.util.function.Consumer

@Singleton
open class PostService(private val postRepository: PostRepository, private val categoryService: CategoryService, //TODO: remove this one
                  private val roleRepository: RoleRepository, private val userRepository: UserRepository, private val tagService: TagService) {
    @Transactional(readOnly = true)
    open fun getNewest(): List<PostViewResource> {
        return postRepository.findNewestFour().map { post: Post? -> PostViewResource.from(post) }
    }

    @Transactional(readOnly = true)
    open fun getPostsBy(typeId: UUID, page: Int, size: Int, postQueryType: PostQueryType?): Page<PostViewResource> {
        return when (postQueryType) {
            PostQueryType.Tag -> this.getPostsByTag(typeId, page, size)
            PostQueryType.Category -> this.getPostsBy(typeId, page, size)
            else -> Page.empty()
        }
    }

    @Transactional(readOnly = true)
    open fun getMostPopularPosts(): List<PostViewResource> {
        return postRepository.mostPopularPosts.map { post: Post? -> PostViewResource.from(post) }
    }

    @Transactional(readOnly = true)
    open fun getPostBy(postId: UUID): PostDetailsResource {
        return PostDetailsResource.from(postRepository.findById(postId)
                .orElseThrow { EntityNotFoundException(Post::class.java, postId) })
    }

    @Transactional
    open fun createPost(postResource: CreatePostResource): PostDetailsResource {
        val postOpt = postRepository.findByTitleAndCategoryIdAndIsDeletedFalse(postResource.title, postResource.categoryId)
        val postExists = this.postExists(postOpt, postResource)
        if (postExists) {
            postOpt.ifPresent { post: Post ->
                throw DuplicateEntryException(Post::class.java, post.id)
            }
        }

        //TODO: mock user till auth
        val role = roleRepository.findById(UUID.fromString("ac3d9fd0-1725-466e-b0a7-00b9cd2161a7"))
        val user = User("email@abv.bg", "somepwd", "Gosho", "Peshev", role.get())
        user.imageUrl = "https://res.cloudinary.com/dpo3vbxnl/image/upload/v1691942376/onlygains/categories/hiking-trail-names_fgpox2.jpg"
        val category = categoryService.getCategoryBy(postResource.categoryId)
        val post = Post(postResource.title, postResource.text, postResource.previewText, user, category)
        val images = postResource.imageUrls.stream().map { i: String? -> PostImage(i, post) }.toList()
        post.addImagesToPost(images)
        user.addPost(post)

        val tags = tagService.getTags(postResource.tags)
        tags.forEach(Consumer { tag: Tag? -> post.addTag(tag) })

        postRepository.save(post)
        log.info("Post has been created, [postId={}]", post.id)

        return PostDetailsResource.from(post)
    }

    private fun postExists(postOptional: Optional<Post>, postResource: CreatePostResource): Boolean {
        if (postOptional.isEmpty) {
            return false
        }

        val post = postOptional.get()
        val similarImageUrls = post.postImages.stream().map { obj: PostImage -> obj.url }.toList() == postResource.imageUrls
        return similarImageUrls && post.category.id == postResource.categoryId && post.title == postResource.title && post.text == postResource.text
    }

    private fun getPostsBy(categoryId: UUID, page: Int, size: Int): Page<PostViewResource> {
        return postRepository.findByCategoryId(categoryId, Pageable.from(page, size)).map { post: Post? -> PostViewResource.from(post) }
    }

    private fun getPostsByTag(tagId: UUID, page: Int, size: Int): Page<PostViewResource> {
        return postRepository.findPostsByTagId(tagId, Pageable.from(page, size))
                .map { post: Post? -> PostViewResource.from(post) }
    }

    companion object {
        private val log: Logger = LoggerProvider.getLogger(PostService::class.java)
    }
}
