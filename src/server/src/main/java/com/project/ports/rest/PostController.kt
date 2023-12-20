package com.project.ports.rest

import com.project.application.models.post.CreatePostResource
import com.project.application.models.post.PostDetailsResource
import com.project.application.models.post.PostViewResource
import com.project.application.services.PostService
import com.project.common.enums.PostQueryType
import io.micronaut.data.model.Page
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.QueryValue
import jakarta.validation.Valid
import java.net.URI
import java.util.UUID

@Controller(value = "/posts")
open class PostController(private val postService: PostService) {
    @Get(uri = "/newest")
    open fun getNewest(): HttpResponse<List<PostViewResource>> {
        return HttpResponse.ok(postService.getNewest())
    }

    @Get(uri = "/popular")
    open fun getPopular(): HttpResponse<List<PostViewResource>> {
        return HttpResponse.ok(postService.getMostPopularPosts())
    }

    @Get(uri = "/details/{id}")
    open fun getPost(@PathVariable("id") id: UUID?): HttpResponse<PostDetailsResource> {
        return HttpResponse.ok(postService.getPostBy(id!!))
    }

    @Get(uri = "/all/{id}")
    open fun getPostsBy(@PathVariable("id") id: UUID?, @QueryValue page: Int, @QueryValue size: Int, @QueryValue type: PostQueryType?): HttpResponse<Page<PostViewResource>> {
        return HttpResponse.ok(postService.getPostsBy(id!!, page, size, type))
    }

    @Post
    open fun createPost(@Body @Valid postResource: CreatePostResource): HttpResponse<PostDetailsResource> {
        val post = postService.createPost(postResource)
        return HttpResponse.created(post, URI.create("/posts"))
    }
}
