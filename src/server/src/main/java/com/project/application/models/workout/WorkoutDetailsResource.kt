package com.project.application.models.workout

import com.project.domain.workout.Workout
import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
data class WorkoutDetailsResource(val id: UUID, val workoutTemplateName: String?) {

    companion object {
        fun from(workout: Workout): WorkoutDetailsResource {
            return WorkoutDetailsResource(workout.id,
                    if (workout.workoutTemplate != null) workout.workoutTemplate!!.name else null)
        }
    }
}
