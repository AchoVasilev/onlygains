package com.project.application.models.workout;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Serdeable
public record CreateWorkoutTemplateResource(@NotNull String name, List<WorkoutExerciseResource> exercises) {
}
