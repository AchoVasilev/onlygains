package com.project.application.models.workout

import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotNull

@Serdeable
data class CreateWorkoutTemplateResource(@NotNull val name: String, val exercises: List<WorkoutExerciseResource>)
