package com.project.domain.workout

import com.project.domain.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.util.UUID

@Entity(name = "original_workout_templates")
class OriginalWorkoutTemplate protected constructor() : BaseEntity() {
    @Id
    val id: UUID = UUID.randomUUID()

    var name: String? = null
        private set

    @OneToMany(mappedBy = "originalWorkoutTemplate")
    val exercises: MutableList<WorkoutExercise> = ArrayList()

    constructor(name: String?, exercises: List<WorkoutExercise>?) : this() {
        this.name = name
        this.exercises.addAll(exercises!!)
    }
}
