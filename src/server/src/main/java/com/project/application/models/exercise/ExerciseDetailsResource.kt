package com.project.application.models.exercise

import com.project.domain.workout.Equipment
import com.project.domain.workout.Exercise
import com.project.domain.workout.MuscleGroup
import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
data class ExerciseDetailsResource(val id: UUID, val name: String?, val gifUrl: String?, val translatedName: String?,
                              val description: String?, val muscleGroups: List<MuscleGroupDetailsResource>,
                              val variations: List<ExerciseResource>, val equipment: List<EquipmentResource>,
                              val mainMuscleGroupsIds: List<String>, val synergisticMuscleGroupsIds: List<String>) {

    companion object {
        fun from(exercise: Exercise): ExerciseDetailsResource {
            return ExerciseDetailsResource(exercise.id, exercise.name,
                    exercise.gifUrl, exercise.translatedName, exercise.description,
                    exercise.muscleGroups.map { muscleGroup: MuscleGroup -> MuscleGroupDetailsResource.from(muscleGroup) },
                    exercise.variations.map { ex: Exercise -> ExerciseResource.from(ex) },
                    exercise.equipment.map { equipment: Equipment -> EquipmentResource.from(equipment) },
                    exercise.mainMuscleGroupsIds, exercise.synergisticMuscleGroupsIds)
        }
    }
}
