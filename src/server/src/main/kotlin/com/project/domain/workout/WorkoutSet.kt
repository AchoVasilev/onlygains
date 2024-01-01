package com.project.domain.workout

import com.project.domain.BaseEntity
import com.project.domain.valueobjects.Weight
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.util.UUID

@Entity(name = "sets")
class WorkoutSet protected constructor() : BaseEntity() {
    @Id
    val id: UUID = UUID.randomUUID()

    @Embedded
    var weight: Weight? = null
        private set
    var repetitions: Int = 0
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id")
    var exercise: WorkoutExercise? = null

    constructor(weight: Double, repetitions: Int) : this() {
        this.weight = Weight(weight)
        this.repetitions = repetitions
    }

    fun changeWeight(weight: Double) {
        this.weight = Weight(weight)
    }

    fun changeRepetitions(repetitions: Int) {
        this.repetitions = repetitions
    }
}
