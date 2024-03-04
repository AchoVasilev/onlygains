package com.project.posts.application

import com.project.application.services.LoggerProvider
import com.project.common.result.OperationResult
import com.project.domain.user.User
import com.project.infrastructure.data.RoleRepository
import com.project.infrastructure.data.UserRepository
import com.project.infrastructure.exceptions.DuplicateEntryException
import com.project.posts.application.models.post.CreatePostResource
import com.project.posts.application.models.post.PostDetailsResource
import com.project.posts.application.models.post.PostViewResource
import com.project.posts.domain.PostImage
import com.project.posts.domain.Post
import com.project.posts.domain.Tag
import com.project.posts.infrastructure.PostRepository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import org.slf4j.Logger
import java.util.Optional
import java.util.UUID

@Singleton
open class PostService(
    private val postRepository: PostRepository,
    private val categoryService: CategoryService, //TODO: remove this one
    private val roleRepository: RoleRepository,
    private val userRepository: UserRepository,
    private val tagService: TagService
) {
    @Transactional(readOnly = true)
    open fun getNewest(): OperationResult<List<PostViewResource>> {
        val posts = this.postRepository.findNewestFour()
        val users = this.userRepository.findAllByIdIn(posts.map { p -> p.userId })

        return OperationResult.success(posts
            .map { post: Post -> PostViewResource.from(post, users.first { u -> u.id == post.userId }) })
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
    open fun getMostPopularPosts(): OperationResult<List<PostViewResource>> {
        val posts = this.postRepository.findMostPopularPosts()
        val users = this.userRepository.findAllByIdIn(posts.map { p -> p.userId })

        return OperationResult.success(posts
            .map { post: Post -> PostViewResource.from(post, users.first { u -> u.id == post.id }) })
    }

    @Transactional(readOnly = true)
    open fun getPostBy(postId: UUID): OperationResult<PostDetailsResource> {
        val post = this.postRepository.findById(postId)
        if (post.isEmpty) {
            return OperationResult.notFound(listOf("Post with id: $postId not found"))
        }

        val user = this.userRepository.findById(post.get().userId)
        if (user.isEmpty) {
            return OperationResult.notFound(listOf("User with id: ${post.get().userId} not found"))
        }

        return OperationResult.success(PostDetailsResource.from(post.get(), user.get()))
    }

    @Transactional
    open fun createPost(postResource: CreatePostResource): PostDetailsResource {
        val postOpt =
            postRepository.findByTitleAndCategoryIdAndIsDeletedFalse(postResource.title, postResource.categoryId)
        val postExists = this.postExists(postOpt, postResource)
        if (postExists) {
            postOpt.ifPresent { post: Post ->
                throw DuplicateEntryException(Post::class.java, post.id)
            }
        }

        //TODO: mock user till auth
        val role = roleRepository.findById(UUID.fromString("ac3d9fd0-1725-466e-b0a7-00b9cd2161a7"))
        val user = User("email@abv.bg", "somepwd", "Gosho", "Peshev", role.get())
        user.imageUrl =
            "https://res.cloudinary.com/dpo3vbxnl/image/upload/v1691942376/onlygains/categories/hiking-trail-names_fgpox2.jpg"
        val category = categoryService.getCategoryBy(postResource.categoryId)
        var post = Post(postResource.title, postResource.text, postResource.previewText, user.id, category)
        val images = postResource.imageUrls.stream().map { i: String -> PostImage(i, post) }.toList()
        post.addImagesToPost(images)

        val tags = tagService.getTags(postResource.tags)
        tags.forEach { tag: Tag -> post.addTag(tag) }

        post = postRepository.save(post)
        log.info("Post has been created, [postId={}]", post.id)

        return PostDetailsResource.from(post, user)
    }

    private fun postExists(postOptional: Optional<Post>, postResource: CreatePostResource): Boolean {
        if (postOptional.isEmpty) {
            return false
        }

        val post = postOptional.get()
        val similarImageUrls =
            post.postImages.stream().map { obj: PostImage -> obj.url }.toList() == postResource.imageUrls
        return similarImageUrls && post.category!!.id == postResource.categoryId && post.title == postResource.title && post.text == postResource.text
    }

    private fun getPostsBy(categoryId: UUID, page: Int, size: Int): Page<PostViewResource> {
        val posts = this.postRepository.findAllByCategoryId(categoryId, Pageable.from(page, size))
        val users = this.userRepository.findAllByIdIn(posts.content.map { p -> p.userId })

        return posts.map { post: Post -> PostViewResource.from(post, users.first { u -> u.id == post.id }) }
    }

    private fun getPostsByTag(tagId: UUID, page: Int, size: Int): Page<PostViewResource> {
        val posts = this.postRepository.findPostsByTagId(tagId, Pageable.from(page, size))
        val users = this.userRepository.findAllByIdIn(posts.content.map { p -> p.userId })

        return posts.map { post: Post -> PostViewResource.from(post, users.first { u -> u.id == post.id }) }
    }

    companion object {
        @JvmStatic
        private val log: Logger = LoggerProvider.getLogger(PostService::class.java)
    }
}
