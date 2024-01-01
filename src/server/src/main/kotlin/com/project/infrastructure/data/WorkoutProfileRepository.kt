package com.project.infrastructure.data

import com.project.domain.user.workout.WorkoutProfile
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.UUID

@Repository
interface WorkoutProfileRepository : CrudRepository<WorkoutProfile, UUID> {

    fun findByUserId(userId: UUID): WorkoutProfile?
}