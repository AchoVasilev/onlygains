package com.project.application.models.user.workout

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class BodyFatDetailsResource(val bodyFat: Double?, val percent: String?) {
}