package com.project.ports.rest

import com.project.application.models.user.workout.CreateBmiResource
import com.project.application.models.user.workout.CreateBmrResource
import com.project.application.models.user.workout.WorkoutProfileDetailsResource
import com.project.application.services.BodyMassService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import java.util.UUID

@Controller("/body-mass")
open class BodyMassController(private val bodyMassService: BodyMassService) {

    @Post("/bmr/{profileId}")
    open fun calculateBmr(@PathVariable profileId: UUID, @Body resource: CreateBmrResource): HttpResponse<WorkoutProfileDetailsResource> {
        return HttpResponse.ok(this.bodyMassService.calculateBmr(profileId, resource))
    }

    @Post("/bmi/{profileId}")
    open fun calculateBmi(@PathVariable profileId: UUID, @Body resource: CreateBmiResource): HttpResponse<WorkoutProfileDetailsResource> {
        return HttpResponse.ok(this.bodyMassService.calculateBmi(profileId, resource))
    }
}