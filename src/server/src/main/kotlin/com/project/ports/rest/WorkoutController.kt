package com.project.ports.rest

import com.project.application.models.workout.CreateWorkoutExerciseResource
import com.project.application.models.workout.UpdateWorkoutExerciseResource
import com.project.application.models.workout.WorkoutDetailsResource
import com.project.application.services.WorkoutService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import java.util.UUID

@Controller("/workouts")
open class WorkoutController(private val workoutService: WorkoutService) {
    @Post
    open fun start(): HttpResponse<WorkoutDetailsResource> {
        return HttpResponse.ok(workoutService.startEmptyWorkout())
    }

    @Post(value = "/start/{templateId}")
    open fun start(@PathVariable templateId: UUID): HttpResponse<WorkoutDetailsResource> {
        return HttpResponse.ok(workoutService.start(templateId))
    }

    @Post("/add-exercise/{workoutId}")
    open fun addExercise(@PathVariable workoutId: UUID, @Body workoutExerciseResource: CreateWorkoutExerciseResource): HttpResponse<WorkoutDetailsResource> {
        return HttpResponse.ok(workoutService.addExerciseToWorkout(workoutId, workoutExerciseResource))
    }

    @Post("/update-exercise")
    open fun updateExercise(workoutExerciseResource: UpdateWorkoutExerciseResource): HttpResponse<WorkoutDetailsResource> {
        return HttpResponse.ok(workoutService.updateExerciseInWorkout(workoutExerciseResource))
    }

    @Post("/finish/{workoutId}")
    open fun finish(@PathVariable workoutId: UUID): HttpResponse<WorkoutDetailsResource> {
        return HttpResponse.ok(workoutService.finish(workoutId))
    }

    @Post("/cancel/{workoutId}")
    open fun cancel(@PathVariable workoutId: UUID): HttpResponse<Unit> {
        workoutService.cancel(workoutId)
        return HttpResponse.ok()
    }
}
