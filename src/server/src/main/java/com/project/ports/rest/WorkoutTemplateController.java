package com.project.ports.rest;

import com.project.application.models.workout.CreateWorkoutTemplateResource;
import com.project.application.models.workout.WorkoutTemplateResource;
import com.project.application.services.WorkoutTemplateService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.validation.Valid;

@Controller(value = "/workout-templates")
public class WorkoutTemplateController {
    private final WorkoutTemplateService workoutTemplateService;

    public WorkoutTemplateController(WorkoutTemplateService workoutTemplateService) {
        this.workoutTemplateService = workoutTemplateService;
    }

    @Post
    public HttpResponse<WorkoutTemplateResource> create(@Body @Valid CreateWorkoutTemplateResource resource) {
        return HttpResponse.ok(this.workoutTemplateService.create(resource));
    }
}
