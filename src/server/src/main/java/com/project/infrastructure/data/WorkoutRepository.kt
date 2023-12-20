package com.project.infrastructure.data

import com.project.domain.workout.Workout
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.UUID

@Repository
interface WorkoutRepository : CrudRepository<Workout, UUID>
