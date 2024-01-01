package com.project.application.models.user.workout

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class BmrDetailsResource(val calories: Double, val unitType: String)
