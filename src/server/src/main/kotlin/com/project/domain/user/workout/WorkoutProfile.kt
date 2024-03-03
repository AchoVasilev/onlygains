package com.project.domain.user.workout

import com.project.application.models.user.workout.UpdateWorkoutProfileResource
import com.project.common.BaseEntity
import com.project.domain.user.Gender
import com.project.domain.user.workout.bmr.BMR
import com.project.domain.valueobjects.EntityId
import com.project.domain.valueobjects.Height
import com.project.domain.valueobjects.Weight
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import java.util.UUID

@Entity
class WorkoutProfile protected constructor() : BaseEntity() {
    @Id
    val id: EntityId = EntityId()
//    @ManyToMany
//    val workoutTemplates: List<WorkoutTemplate> = listOf()
//    @OneToOne
//    var workoutHistory: WorkoutHistory? = null

    var userId: UUID? = null
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

    constructor(userId: UUID) : this() {
        this.userId = userId
    }

    fun calculateBmr(bmrEquation: BMREquation, activityLevel: ActivityLevel?) {
        requireNotNull(this.gender)
        requireNotNull(this.weight)
        requireNotNull(this.height)
        requireNotNull(this.age)

        this.bmr =
            BMR(bmrEquation, this.gender!!, this.weight!!, this.height!!, this.age!!, activityLevel, this.bodyFat)
    }

    fun calculateBmi() {
        requireNotNull(this.weight)
        requireNotNull(this.weight)
        this.bmi = BMI(this.height!!, this.weight!!)
    }

    fun updateIfNeeded(resource: UpdateWorkoutProfileResource) {
        this.bodyFat = BodyFat(resource.bodyFat)
        this.height = Height(resource.height)
        this.weight = Weight(resource.weight)
        this.gender = resource.gender
        this.age = resource.age

        if (this.height?.height == null || this.weight?.weight == null) {
            this.bmi = null
        }
    }
}