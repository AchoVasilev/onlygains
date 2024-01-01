package com.project.domain.user.workout

import jakarta.persistence.Embeddable

@Embeddable
class BodyFat {
    var bodyFat: Double? = null
        private set
    val percent: String = "%"

    protected constructor()

    constructor(bodyFat: Double) {
        this.bodyFat = bodyFat
    }

    fun fromPercent() : Double {
        return this.bodyFat?.div(100.0) ?: Double.NaN
    }
}