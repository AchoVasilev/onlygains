package com.project.ports.rest

import com.project.application.models.exercise.EquipmentResource
import com.project.application.services.EquipmentService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller(value = "/equipment")
open class EquipmentController(private val equipmentService: EquipmentService) {
    @Get
    open fun getAll(): HttpResponse<List<EquipmentResource>> {
        return HttpResponse.ok(equipmentService.getAll())
    }
}
