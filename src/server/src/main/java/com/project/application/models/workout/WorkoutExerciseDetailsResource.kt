package com.project.application.models.workout

import com.project.domain.workout.WorkoutExercise
import com.project.domain.workout.WorkoutSet
import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
data class WorkoutExerciseDetailsResource(val id: UUID, val exerciseId: UUID?, val sets: List<SetResource>) {

    companion object {
        fun from(exercise: WorkoutExercise): WorkoutExerciseDetailsResource {
            return WorkoutExerciseDetailsResource(exercise.id, exercise.exerciseId, exercise.sets.map { set: WorkoutSet -> SetResource.from(set) })
        }
    }
}
