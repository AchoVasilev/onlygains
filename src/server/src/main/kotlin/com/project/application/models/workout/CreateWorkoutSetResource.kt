package com.project.application.models.workout

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CreateWorkoutSetResource(val weight: Double, val repetitions: Int)