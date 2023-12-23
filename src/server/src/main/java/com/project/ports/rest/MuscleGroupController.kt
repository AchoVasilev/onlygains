package com.project.ports.rest

import com.project.application.models.exercise.MuscleGroupDetailsResource
import com.project.application.services.MuscleGroupService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller(value = "/muscle-groups")
open class MuscleGroupController(private val muscleGroupService: MuscleGroupService) {
    @Get
    open fun getAll(): HttpResponse<List<MuscleGroupDetailsResource>> {
        return HttpResponse.ok(muscleGroupService.getAll())
    }
}
