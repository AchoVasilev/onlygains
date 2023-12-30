package com.project.infrastructure.data

import com.project.domain.workout.MuscleGroup
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface MuscleGroupRepository : CrudRepository<MuscleGroup, String> {
    fun findByIdIn(ids: List<String>): List<MuscleGroup>
}
