package com.project.domain.user.workout.bmr

import com.project.domain.user.Gender
import com.project.domain.user.workout.BodyFat
import com.project.domain.valueobjects.Height
import com.project.domain.valueobjects.Weight

fun interface BMRCalculator {
    fun calculate(gender: Gender, weight: Weight, height: Height, age: Int, bodyFat: BodyFat?) : Double
}