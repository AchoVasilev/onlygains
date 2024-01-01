package com.project.application.models.user.workout

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CreateBmiResource(val height: Double?, val weight: Double?)