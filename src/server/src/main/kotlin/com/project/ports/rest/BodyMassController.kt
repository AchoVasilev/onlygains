package com.project.ports.rest

import com.project.application.models.user.workout.BmrDetailsResource
import com.project.application.models.user.workout.CreateBmrResource
import com.project.application.services.BodyMassService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import java.util.UUID

@Controller("/body-mass")
open class BodyMassController(private val bodyMassService: BodyMassService) {

    @Post("/bmr/{userId}")
    open fun calculateBmr(@PathVariable userId: UUID, @Body resource: CreateBmrResource): HttpResponse<BmrDetailsResource> {
        return HttpResponse.ok(this.bodyMassService.calculateBmr(userId, resource))
    }
}