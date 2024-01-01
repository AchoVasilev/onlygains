package com.project.application.models.user.workout

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class WeightDetailsResource(val weight: Double?, val weightType: String?)
