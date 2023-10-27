package com.project.ports.rest;

import com.project.application.models.workout.WorkoutDetailsResource;
import com.project.application.services.WorkoutService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

@Controller("/workouts")
public class WorkoutController {
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @Post
    public HttpResponse<WorkoutDetailsResource> start() {
        return HttpResponse.ok(this.workoutService.startEmptyWorkout());
    }
}
