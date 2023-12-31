package com.project.ports.rest

import com.project.application.models.exercise.CreateExerciseResource
import com.project.application.models.exercise.ExerciseDetailsResource
import com.project.application.models.exercise.ExerciseResource
import com.project.application.services.ExerciseService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.QueryValue
import jakarta.validation.Valid
import java.util.UUID

@Controller(value = "/exercises")
open class ExerciseController(private val exerciseService: ExerciseService) {
    @Get(uri = "/{id}")
    open fun getById(@PathVariable("id") id: UUID): HttpResponse<ExerciseDetailsResource> {
        return HttpResponse.ok(exerciseService.getBy(id))
    }

    @Get("/variations")
    open fun getBy(@QueryValue search: String): HttpResponse<List<ExerciseResource>> {
        return HttpResponse.ok(exerciseService.getBy(search))
    }

    @Post
    open fun create(@Body @Valid resource: CreateExerciseResource): HttpResponse<ExerciseDetailsResource> {
        return HttpResponse.ok(exerciseService.create(resource))
    }
}
