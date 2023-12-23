package com.project.application.models.workout

import com.project.domain.workout.WorkoutExercise
import com.project.domain.workout.WorkoutTemplate
import io.micronaut.serde.annotation.Serdeable
import java.time.ZonedDateTime
import java.util.UUID

@Serdeable
data class WorkoutTemplateResource(val id: UUID, val name: String?, val exercises: List<WorkoutExerciseDetailsResource>,
                                   val workoutIds: List<UUID>, val createdAt: ZonedDateTime) {
    companion object {
        fun from(template: WorkoutTemplate): WorkoutTemplateResource {
            return WorkoutTemplateResource(
                    template.id,
                    template.name,
                    template.exercises.map { exercise: WorkoutExercise? -> WorkoutExerciseDetailsResource.from(exercise!!) },
                    template.exercises.map { exercise: WorkoutExercise? -> exercise!!.id },
                    template.createdAt)
        }
    }
}
