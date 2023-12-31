package com.project.domain.user

import com.project.domain.BaseEntity
import com.project.domain.valueobjects.BMI
import com.project.domain.valueobjects.Height
import com.project.domain.valueobjects.Weight
import com.project.domain.workout.WorkoutHistory
import com.project.domain.workout.WorkoutTemplate
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
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
    var bmi: BMI? = null

    constructor(user: User) : this() {
        this.user = user
    }
}