package com.project.domain.user.workout.bmr

import com.project.domain.user.Gender
import com.project.domain.user.workout.ActivityLevel
import com.project.domain.user.workout.BMREquation
import com.project.domain.user.workout.BodyFat
import com.project.domain.valueobjects.Height
import com.project.domain.valueobjects.Weight
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import kotlin.math.ceil

@Embeddable
class BMR protected constructor() {
    var calories: Double? = null
        private set

    val unitType: String = "kcal"

    @Enumerated(value = EnumType.STRING)
    var bmrEquation: BMREquation? = null

    @Enumerated(value = EnumType.STRING)
    var activityLevel: ActivityLevel? = null

    constructor(
        bmrEquation: BMREquation,
        gender: Gender,
        weight: Weight,
        height: Height,
        age: Int,
        activityLevel: ActivityLevel?,
        bodyFat: BodyFat? = null
    ) : this() {
        this.bmrEquation = bmrEquation
        this.activityLevel = activityLevel
        this.calories = this.calculateBmr(gender, weight, height, age, bodyFat)
    }

    private fun calculateBmr(gender: Gender, weight: Weight, height: Height, age: Int, bodyFat: BodyFat?): Double {
        val bmrResult = this.bmrEquation!!.calculateBMR(gender, weight, height, age, bodyFat)
        return if (this.activityLevel != null) ceil(bmrResult * this.activityLevel!!.getNumericValue())
        else ceil(this.bmrEquation!!.calculateBMR(gender, weight, height, age, bodyFat))
    }
}