package com.project.ports.rest;

import com.project.application.models.exercise.MuscleGroupDetailsResource;
import com.project.application.services.MuscleGroupService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller(value = "/muscle-groups")
public class MuscleGroupController {
    private final MuscleGroupService muscleGroupService;

    public MuscleGroupController(MuscleGroupService muscleGroupService) {
        this.muscleGroupService = muscleGroupService;
    }

    @Get
    public HttpResponse<List<MuscleGroupDetailsResource>> getAll() {
        return HttpResponse.ok(this.muscleGroupService.getAll());
    }
}
