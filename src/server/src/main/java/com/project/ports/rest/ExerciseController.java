package com.project.ports.rest;

import com.project.application.models.exercise.CreateExerciseResource;
import com.project.application.models.exercise.ExerciseDetailsResource;
import com.project.application.models.exercise.ExerciseResource;
import com.project.application.services.ExerciseService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@Controller(value = "/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @Get(uri = "/{id}")
    public HttpResponse<ExerciseDetailsResource> getById(@PathVariable("id") UUID id) {
        return HttpResponse.ok(this.exerciseService.getBy(id));
    }

    @Get("/variations")
    public HttpResponse<List<ExerciseResource>> getBy(@QueryValue String search) {
        return HttpResponse.ok(this.exerciseService.findBy(search));
    }

    @Post
    public HttpResponse<ExerciseDetailsResource> create(@Body @Valid CreateExerciseResource resource) {
        return HttpResponse.ok(this.exerciseService.create(resource));
    }
}
