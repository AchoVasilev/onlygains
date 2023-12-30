package com.project.application.models.image

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class ImageResponseResource(val url: String, val name: String)
