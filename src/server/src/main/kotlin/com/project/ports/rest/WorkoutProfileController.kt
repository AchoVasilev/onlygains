package com.project.ports.rest

import com.project.application.models.user.workout.WorkoutProfileDetailsResource
import com.project.application.services.WorkoutProfileService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import java.util.UUID

@Controller("/users")
open class WorkoutProfileController(private val workoutProfileService: WorkoutProfileService) {

    @Get("/{userId}/workout-profile")
    open fun getById(@PathVariable userId: UUID): HttpResponse<WorkoutProfileDetailsResource> {
        return HttpResponse.ok(this.workoutProfileService.getBy(userId))
    }
}