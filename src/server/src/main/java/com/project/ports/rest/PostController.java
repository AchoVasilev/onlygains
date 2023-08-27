package com.project.ports.rest;

import com.project.application.models.post.CreatePostResource;
import com.project.application.models.post.PostViewResource;
import com.project.application.services.PostService;
import io.micronaut.data.model.Page;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Controller(value = "/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Get(uri = "/newest")
    public HttpResponse<List<PostViewResource>> getNewest() {
        return HttpResponse.ok(this.postService.getNewest());
    }

    @Get(uri = "/popular")
    public HttpResponse<List<PostViewResource>> getPopular() {
        return HttpResponse.ok(this.postService.getMostPopularPosts());
    }

    @Get(uri = "/{id}")
    public HttpResponse<PostViewResource> getPost(@PathVariable("id") UUID id) {
        return HttpResponse.ok();
    }

    @Get(uri = "/{categoryName}/{categoryId}")
    public HttpResponse<Page<PostViewResource>> getPostsBy(@PathVariable("categoryName") String categoryName,
                                                           @PathVariable("categoryId") UUID categoryId, @QueryValue int currentPage) {
        return HttpResponse.ok(this.postService.getPostsBy(categoryId, currentPage));
    }

    @Get(uri = "/tags/{tagId}")
    public HttpResponse<Page<PostViewResource>> getPostsBy(@PathVariable("tagId") UUID tagId, @QueryValue int currentPage) {
        return HttpResponse.ok(this.postService.getPostsByTag(tagId, currentPage));
    }

    @Post
    public HttpResponse<Void> createPost(@Body @Valid CreatePostResource postResource) {
        this.postService.createPost(postResource);
        return HttpResponse.created(URI.create("/posts"));
    }
}
