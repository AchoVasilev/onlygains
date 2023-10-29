package com.project.ports.rest;

import com.project.application.models.workout.CreateWorkoutExerciseResource;
import com.project.application.models.workout.UpdateWorkoutExerciseResource;
import com.project.application.models.workout.WorkoutDetailsResource;
import com.project.application.services.WorkoutService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;

import java.util.UUID;

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

    @Post(value = "/start/{templateId}")
    public HttpResponse<WorkoutDetailsResource> start(@PathVariable UUID templateId) {
        return HttpResponse.ok(this.workoutService.start(templateId));
    }

    @Post("/add-exercise/{workoutId}")
    public HttpResponse<WorkoutDetailsResource> addExercise(@PathVariable UUID workoutId, @Body CreateWorkoutExerciseResource workoutExerciseResource) {
        return HttpResponse.ok(this.workoutService.addExerciseToWorkout(workoutId, workoutExerciseResource));
    }

    @Post("/update-exercise")
    public HttpResponse<WorkoutDetailsResource> updateExercise(UpdateWorkoutExerciseResource workoutExerciseResource) {
        return HttpResponse.ok(this.workoutService.updateExerciseInWorkout(workoutExerciseResource));
    }

    @Post("/finish/{workoutId}")
    public HttpResponse<WorkoutDetailsResource> finish(@PathVariable UUID workoutId) {
        return HttpResponse.ok(this.workoutService.finish(workoutId));
    }

    @Post("/cancel/{workoutId}")
    public HttpResponse<Void> cancel(@PathVariable UUID workoutId) {
        this.workoutService.cancel(workoutId);
        return HttpResponse.ok();
    }
}
