package com.project.domain.user.workout

import com.project.domain.user.Gender
import com.project.domain.user.workout.bmr.harrisBenedictBmrCalculator
import com.project.domain.user.workout.bmr.katchMcArdleBmrCalculator
import com.project.domain.user.workout.bmr.mifflinStJoerBmrCalculator
import com.project.domain.valueobjects.Height
import com.project.domain.valueobjects.Weight

enum class BMREquation {
    MIFFLIN_ST_JEOR {
        override fun calculateBMR(gender: Gender, weight: Weight, height: Height, age: Int, bodyFat: BodyFat?) : Double {
            return mifflinStJoerBmrCalculator.calculate(gender, weight, height, age, bodyFat)
        }
    },
    REVISED_HARRIS_BENEDICT {
        override fun calculateBMR(gender: Gender, weight: Weight, height: Height, age: Int, bodyFat: BodyFat?): Double {
            return harrisBenedictBmrCalculator.calculate(gender, weight, height, age, bodyFat)
        }
    },
    KATCH_MCARDLE {
        override fun calculateBMR(gender: Gender, weight: Weight, height: Height, age: Int, bodyFat: BodyFat?): Double {
            return katchMcArdleBmrCalculator.calculate(gender, weight, height, age, bodyFat)
        }
    };

    abstract fun calculateBMR(gender: Gender, weight: Weight, height: Height, age: Int, bodyFat: BodyFat? = null) : Double
}