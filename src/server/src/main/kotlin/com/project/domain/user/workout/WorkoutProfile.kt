package com.project.domain.user.workout

import com.project.application.models.user.workout.CreateBmiResource
import com.project.application.models.user.workout.CreateBmrResource
import com.project.application.models.user.workout.UpdateWorkoutProfileResource
import com.project.domain.BaseEntity
import com.project.domain.user.Gender
import com.project.domain.user.workout.bmr.BMR
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
    val id: UUID = UUID.randomUUID()
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

    fun calculateBmr(bmrEquation: BMREquation) {
        this.bmr = BMR(bmrEquation, this.gender!!, this.weight!!, this.height!!, this.age!!, this.bodyFat)
    }

    fun calculateBmi() {
        this.bmi = BMI(this.height!!, this.weight!!)
    }

    fun updateIfNeeded(resource: CreateBmrResource) {
        if (resource.bodyFat != null) this.bodyFat = BodyFat(resource.bodyFat)
        if (resource.height != null) this.height = Height(resource.height)
        if (resource.weight != null) this.weight = Weight(resource.weight)
        if (resource.gender != null) this.gender = resource.gender
        if (resource.age != null) this.age = resource.age
    }

    fun updateIfNeeded(resource: UpdateWorkoutProfileResource) {
        if (resource.bodyFat != null) this.bodyFat = BodyFat(resource.bodyFat)
        if (resource.height != null) this.height = Height(resource.height)
        if (resource.weight != null) this.weight = Weight(resource.weight)
        if (resource.gender != null) this.gender = resource.gender
        if (resource.age != null) this.age = resource.age
    }

    fun updateIfNeeded(resource: CreateBmiResource) {
        if (resource.height != null) this.height = Height(resource.height)
        if (resource.weight != null) this.weight = Weight(resource.weight)
    }
}