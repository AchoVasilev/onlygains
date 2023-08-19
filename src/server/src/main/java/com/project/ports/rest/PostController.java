package com.project.ports.rest;

import com.project.application.models.post.CreatePostResource;
import com.project.application.models.post.PostInitialViewResource;
import com.project.application.services.PostService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

@Controller(value = "/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Get(uri = "/newest")
    public HttpResponse<List<PostInitialViewResource>> getNewest() {
        return HttpResponse.ok(this.postService.getNewest());
    }

    @Post
    public HttpResponse<Void> createPost(@Body @Valid CreatePostResource postResource) {
        this.postService.createPost(postResource);
        return HttpResponse.created(URI.create("/posts"));
    }
}
