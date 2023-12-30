package com.project.application.services

import com.project.application.models.exercise.CreateExerciseResource
import com.project.application.models.exercise.ExerciseDetailsResource
import com.project.application.models.exercise.ExerciseResource
import com.project.domain.workout.Exercise
import com.project.infrastructure.data.ExerciseRepository
import com.project.infrastructure.exceptions.DuplicateEntryException
import com.project.infrastructure.exceptions.EntityNotFoundException
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
open class ExerciseService(private val exerciseRepository: ExerciseRepository, private val muscleGroupService: MuscleGroupService, private val equipmentService: EquipmentService) {
    @Transactional(readOnly = true)
    open fun getBy(id: UUID): Exercise {
        return exerciseRepository.findById(id)
                .orElseThrow { EntityNotFoundException(Exercise::class, id) }
    }

    @Transactional
    open fun create(exerciseResource: CreateExerciseResource): ExerciseDetailsResource {
        val exerciseOpt = exerciseRepository.findByName(exerciseResource.name)
        if (exerciseOpt.isPresent) {
            throw DuplicateEntryException(Exercise::class.java, exerciseOpt.get().id)
        }

        val exercise = Exercise(exerciseResource.name, exerciseResource.translatedName, exerciseResource.description, exerciseResource.imageUrl, exerciseResource.gifUrl)
        exerciseResource.variations.ifPresent { variationIds: List<UUID> ->
            val variations = this.getBy(variationIds)
            exercise.addVariations(variations)
        }

        exerciseResource.mainMuscleGroupsIds.ifPresent { muscleGroupIds: List<String> ->
            val muscleGroups = muscleGroupService.findBy(muscleGroupIds)
            exercise.addMuscleGroups(muscleGroups)
            exercise.addMainMuscleGroupsIds(muscleGroupIds)
        }

        exerciseResource.synergisticMuscleGroupsIds.ifPresent { muscleGroupIds: List<String> ->
            val muscleGroups = muscleGroupService.findBy(muscleGroupIds)
            exercise.addMuscleGroups(muscleGroups)
            exercise.addSynergisticMuscleGroupIds(muscleGroupIds)
        }

        exerciseResource.equipment.ifPresent { equipmentIds: List<UUID> ->
            val equipment = equipmentService.getBy(equipmentIds)
            exercise.addEquipment(equipment)
        }

        val result = exerciseRepository.save(exercise)

        return ExerciseDetailsResource.from(result)
    }

    @Transactional(readOnly = true)
    open fun getBy(search: String): List<ExerciseResource> {
        return exerciseRepository.findSimilarExercises(search)
    }

    @Transactional(readOnly = true)
    open fun getBy(ids: List<UUID>): List<Exercise> {
        return exerciseRepository.findByIdIn(ids)
    }
}
