package com.project.application.models.workout

import com.project.domain.workout.WorkoutSet
import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
data class WorkoutSetResource(val id: UUID?, val repetitions: Int, val weight: Double) {

    companion object {
        @JvmStatic
        fun from(set: WorkoutSet): WorkoutSetResource {
            return WorkoutSetResource(set.id, set.repetitions, set.weight!!.weight!!)
        }
    }
}
