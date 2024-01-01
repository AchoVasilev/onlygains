package com.project.domain.user.workout

import com.project.domain.BaseEntity
import com.project.domain.user.Gender
import com.project.domain.user.User
import com.project.domain.user.workout.bmr.BMR
import com.project.domain.valueobjects.Height
import com.project.domain.valueobjects.Weight
import com.project.domain.workout.WorkoutHistory
import com.project.domain.workout.WorkoutTemplate
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne
import java.util.UUID

@Entity
class WorkoutProfile protected constructor() : BaseEntity() {
    @Id
    val id: UUID = UUID.randomUUID()

    @ManyToMany
    val workoutTemplates: List<WorkoutTemplate> = listOf()

    @OneToOne
    var workoutHistory: WorkoutHistory? = null

    @OneToOne
    var user: User? = null
        private set

    @Embedded
    var height: Height? = null

    @Embedded
    var weight: Weight? = null

    @Embedded
    var bodyFat: BodyFat? = null

    @Enumerated(EnumType.STRING)
    var gender: Gender? = null

    var age: Int? = null

    @Embedded
    var bmi: BMI? = null
        private set

    @Embedded
    var bmr: BMR? = null
        private set

    constructor(user: User) : this() {
        this.user = user
    }

    fun calculateBmr(bmrEquation: BMREquation) {
        this.bmr = BMR(bmrEquation, this.gender!!, this.weight!!, this.height!!, this.age!!, this.bodyFat)
    }
}