package com.project.application.models.workout

import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
data class WorkoutExerciseResource(val id: UUID, val sets: List<SetResource>)
