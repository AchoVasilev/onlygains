package com.project.ports.rest

import com.project.application.models.user.workout.UpdateWorkoutProfileResource
import com.project.application.models.user.workout.WorkoutProfileDetailsResource
import com.project.application.services.WorkoutProfileService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Patch
import io.micronaut.http.annotation.PathVariable
import java.util.UUID

@Controller("/users")
open class WorkoutProfileController(private val workoutProfileService: WorkoutProfileService) {

    @Get("/{userId}/workout-profile")
    open fun getById(@PathVariable userId: UUID): HttpResponse<WorkoutProfileDetailsResource> {
        return HttpResponse.ok(this.workoutProfileService.getBy(userId))
    }

    @Patch("/{profileId}/workout-profile")
    open fun updateProfile(@PathVariable profileId: UUID, @Body resource: UpdateWorkoutProfileResource): HttpResponse<WorkoutProfileDetailsResource> {
        return HttpResponse.ok(this.workoutProfileService.update(profileId, resource))
    }
}