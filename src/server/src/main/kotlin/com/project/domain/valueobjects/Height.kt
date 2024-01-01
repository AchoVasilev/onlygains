package com.project.domain.valueobjects

import jakarta.persistence.Embeddable

@Embeddable
class Height {
    var height: Double? = null
        private set

    val heightType: String = "cm"

    constructor(height: Double) {
        this.height = height
    }

    protected constructor()

    fun toMeters(): Double {
        return this.height!! / 100
    }
}