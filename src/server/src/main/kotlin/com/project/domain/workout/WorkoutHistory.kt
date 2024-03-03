package com.project.domain.workout

import com.project.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

@Entity
class WorkoutHistory protected constructor(): BaseEntity() {
    @Id
    val id: UUID = UUID.randomUUID()

    var workoutId: UUID? = null
        private set

    constructor(workoutId: UUID) : this() {
        this.workoutId = workoutId
    }
}
