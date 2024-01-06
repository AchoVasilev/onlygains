package com.project.domain.user.workout

enum class ActivityLevel(private val numericValue: Double, private val description: String) {
    Sedentary(1.2, "Ниска/никаква физическа активност, работа на бюро"),
    LightlyActive(1.375, "Леки упражнения/спортуване 1-3 дни седмично"),
    ModeratelyActive(1.55, "Средно ниво на активност/спортуване 3-5 дни седмично"),
    VeryActive(1.725, "Високо ниво на активност/спортуване 6-7 дни седмично"),
    ExtremelyActive(1.9, "Много високо ниво на активност/спортуване и физически труд или трениране 2х на ден, професионален спортист и т.н.");

    fun getNumericValue(): Double {
        return this.numericValue
    }

    fun getDescription(): String {
        return this.description
    }
}