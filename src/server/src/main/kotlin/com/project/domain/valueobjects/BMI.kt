package com.project.domain.valueobjects

import jakarta.persistence.Embeddable

@Embeddable
class BMI {
    var bmi: Double = 0.0

    protected constructor()

    constructor(height: Height, weight: Weight) {
        this.bmi = this.calculateBmi(height.height, weight.weight)
    }

    private fun calculateBmi(height: Double, weight: Double) : Double {
        val cmToM = height / 100

        return weight / (cmToM * cmToM)
    }
}