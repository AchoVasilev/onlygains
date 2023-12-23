package com.project.application.models.workout

import com.project.domain.workout.WorkoutSet
import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
data class SetResource(val id: UUID, val repetitions: Int, val weight: Double) {

    companion object {
        fun from(set: WorkoutSet): SetResource {
            return SetResource(set.id, set.repetitions, set.weight!!.weight)
        }
    }
}
