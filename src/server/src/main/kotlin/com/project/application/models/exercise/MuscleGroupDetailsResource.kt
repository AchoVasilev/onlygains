package com.project.application.models.exercise

import com.project.domain.workout.MuscleGroup
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class MuscleGroupDetailsResource(val id: String?, val name: String?, val translatedName: String?) {
    companion object {
        fun from(muscleGroup: MuscleGroup): MuscleGroupDetailsResource {
            return MuscleGroupDetailsResource(muscleGroup.id, muscleGroup.name, muscleGroup.translatedName)
        }
    }
}
