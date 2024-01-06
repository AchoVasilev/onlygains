package com.project.application.models.user.workout

import com.project.domain.user.workout.ActivityLevel
import com.project.domain.user.workout.BMREquation
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotBlank

@Serdeable
data class CreateBmrResource(
    @NotBlank
    val bmrEquation: BMREquation,
    @NotBlank
    val activityLevel: ActivityLevel
)
