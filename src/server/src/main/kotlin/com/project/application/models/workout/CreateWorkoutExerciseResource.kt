package com.project.application.models.workout

import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
data class CreateWorkoutExerciseResource(val exerciseId: UUID, val sets: List<CreateWorkoutSetResource>)
