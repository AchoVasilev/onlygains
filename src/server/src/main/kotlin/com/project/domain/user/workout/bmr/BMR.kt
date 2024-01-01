package com.project.domain.user.workout.bmr

import com.project.domain.user.Gender
import com.project.domain.user.workout.BMREquation
import com.project.domain.user.workout.BodyFat
import com.project.domain.valueobjects.Height
import com.project.domain.valueobjects.Weight
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
class BMR {
    var calories: Double? = null
        private set

    val unitType: String = "kcal"

    @Enumerated(value = EnumType.STRING)
    var bmrEquation : BMREquation? = null

    protected constructor()

    constructor(bmrEquation: BMREquation, gender: Gender, weight: Weight, height: Height, age: Int, bodyFat: BodyFat? = null) {
        this.bmrEquation = bmrEquation
        this.calories = this.calculateBmr(gender, weight, height, age, bodyFat)
    }

    private fun calculateBmr(gender: Gender, weight: Weight, height: Height, age: Int, bodyFat: BodyFat?) : Double {
        return this.bmrEquation!!.calculateBMR(gender, weight, height, age, bodyFat)
    }
}