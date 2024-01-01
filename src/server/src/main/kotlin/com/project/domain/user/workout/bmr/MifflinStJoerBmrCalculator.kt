package com.project.domain.user.workout.bmr

import com.project.domain.user.Gender
import com.project.domain.user.workout.BodyFat
import com.project.domain.valueobjects.Height
import com.project.domain.valueobjects.Weight

class MifflinStJoerBmrCalculator {
    companion object : BMRCalculator {
        @JvmStatic
        override fun calculate(gender: Gender, weight: Weight, height: Height, age: Int, bodyFat: BodyFat?): Double {
            return if (gender == Gender.M) 10 * weight.weight!! + 6.25 * height.height!! - 5 * age + 5
            else 10 * weight.weight!! + 6.25 * height.height!! - 5 * age - 161
        }
    }
}