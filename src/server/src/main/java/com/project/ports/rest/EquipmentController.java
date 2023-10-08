package com.project.ports.rest;

import com.project.application.models.exercise.EquipmentResource;
import com.project.application.services.EquipmentService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller(value = "/equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @Get
    public HttpResponse<List<EquipmentResource>> getAll() {
        return HttpResponse.ok(this.equipmentService.getAll());
    }
}
