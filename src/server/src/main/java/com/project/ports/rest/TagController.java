package com.project.ports.rest;

import com.project.application.models.tag.TagViewResource;
import com.project.application.services.TagService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller(value = "/tags")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @Get
    public HttpResponse<List<TagViewResource>> getTags() {
        return HttpResponse.ok(this.tagService.getTags());
    }
}
