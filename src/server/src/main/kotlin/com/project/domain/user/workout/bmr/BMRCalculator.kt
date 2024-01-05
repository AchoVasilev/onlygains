package com.project.domain.user.workout.bmr

import com.project.domain.user.Gender
import com.project.domain.user.workout.BodyFat
import com.project.domain.valueobjects.Height
import com.project.domain.valueobjects.Weight

fun interface BMRCalculator {
    fun calculate(gender: Gender, weight: Weight, height: Height, age: Int, bodyFat: BodyFat?): Double
}

val harrisBenedictBmrCalculator =
    BMRCalculator { gender: Gender, weight: Weight, height: Height, age: Int, _: BodyFat? ->
        if (gender == Gender.M) 13.397 * weight.weight!! + 4.799 * height.height!! - 5.677 * age + 88.362
        else 9.247 * weight.weight!! + 3.098 * height.height!! - 4.330 * age + 447.593
    }

val mifflinStJoerBmrCalculator =
    BMRCalculator { gender: Gender, weight: Weight, height: Height, age: Int, _: BodyFat? ->
        if (gender == Gender.M) 10 * weight.weight!! + 6.25 * height.height!! - 5 * age + 5
        else 10 * weight.weight!! + 6.25 * height.height!! - 5 * age - 161
    }

val katchMcArdleBmrCalculator = BMRCalculator { _: Gender, weight: Weight, _: Height, _: Int, bodyFat: BodyFat? ->
    val leanBodyMass = (1 - bodyFat!!.fromPercent()) * weight.weight!!
    370 + (9.82 * leanBodyMass)
}