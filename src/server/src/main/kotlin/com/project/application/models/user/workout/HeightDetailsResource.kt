package com.project.application.models.user.workout

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class HeightDetailsResource(val height: Double?, val heightType: String?) {
}