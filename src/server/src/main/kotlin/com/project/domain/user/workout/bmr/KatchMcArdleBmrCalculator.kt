package com.project.domain.user.workout.bmr

import com.project.domain.user.Gender
import com.project.domain.user.workout.BodyFat
import com.project.domain.valueobjects.Height
import com.project.domain.valueobjects.Weight

class KatchMcArdleBmrCalculator {

    companion object : BMRCalculator {
        @JvmStatic
        override fun calculate(gender: Gender, weight: Weight, height: Height, age: Int, bodyFat: BodyFat?): Double {
            val leanBodyMass = (1 - bodyFat!!.fromPercent()) * weight.weight!!
            return 370 + (9.82 * leanBodyMass)
        }
    }
}