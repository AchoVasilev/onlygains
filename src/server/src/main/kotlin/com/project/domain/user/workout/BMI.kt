package com.project.domain.user.workout

import com.project.domain.valueobjects.Height
import com.project.domain.valueobjects.Weight
import jakarta.persistence.Embeddable

@Embeddable
class BMI {
    var bmi: Double? = null
        private set

    protected constructor()

    constructor(height: Height, weight: Weight) {
        this.bmi = this.calculateBmi(height.toMeters(), weight.weight)
    }

    private fun calculateBmi(height: Double, weight: Double) : Double {
        return weight / (height * height)
    }
}