package com.project.application.models.exercise

import com.project.domain.workout.Exercise
import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
data class ExerciseResource(val id: UUID, val imageUrl: String?, val name: String?, val translatedName: String?) {
    companion object {
        fun from(exercise: Exercise): ExerciseResource {
            return ExerciseResource(exercise.id, exercise.imageUrl, exercise.name, exercise.translatedName)
        }
    }
}
