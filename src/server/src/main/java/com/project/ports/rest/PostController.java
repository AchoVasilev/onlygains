package com.project.ports.rest;

import com.project.application.models.post.PostInitialViewResource;
import com.project.application.services.PostService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

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
}
