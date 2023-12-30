package com.project.application.services

import com.project.application.models.exercise.MuscleGroupDetailsResource
import com.project.domain.workout.MuscleGroup
import com.project.infrastructure.data.MuscleGroupRepository
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton

@Singleton
open class MuscleGroupService(private val muscleGroupRepository: MuscleGroupRepository) {
    @Transactional(readOnly = true)
    open fun findBy(ids: List<String>): List<MuscleGroup> {
        return muscleGroupRepository.findByIdIn(ids)
    }

    @Transactional(readOnly = true)
    open fun getAll(): List<MuscleGroupDetailsResource> {
        return muscleGroupRepository.findAll().map { muscleGroup: MuscleGroup -> MuscleGroupDetailsResource.from(muscleGroup) }
    }
}
