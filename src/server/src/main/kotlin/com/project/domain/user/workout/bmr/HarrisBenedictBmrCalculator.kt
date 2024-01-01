package com.project.domain.user.workout.bmr

import com.project.domain.user.Gender
import com.project.domain.user.workout.BodyFat
import com.project.domain.valueobjects.Height
import com.project.domain.valueobjects.Weight

class HarrisBenedictBmrCalculator {

    companion object Calculator : BMRCalculator {
        @JvmStatic
        override fun calculate(gender: Gender, weight: Weight, height: Height, age: Int, bodyFat: BodyFat?): Double {
            return if (gender == Gender.M) 13.397 * weight.weight!! + 4.799 * height.height!! - 5.677 * age + 88.362
            else 9.247 * weight.weight!! + 3.098 * height.height!! - 4.330 * age + 447.593
        }
    }
}