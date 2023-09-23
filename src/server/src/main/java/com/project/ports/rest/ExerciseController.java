package com.project.ports.rest;

import com.project.application.models.exercise.CreateExerciseResource;
import com.project.application.models.exercise.ExerciseDetailsResource;
import com.project.application.services.ExerciseService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;

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

    @Post
    public HttpResponse<ExerciseDetailsResource> create(CreateExerciseResource resource) {
        return HttpResponse.ok(this.exerciseService.create(resource));
    }
}
