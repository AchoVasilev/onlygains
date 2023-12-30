package com.project.infrastructure.data

import com.project.domain.workout.WorkoutHistory
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.UUID

@Repository
interface WorkoutHistoryRepository : CrudRepository<WorkoutHistory, UUID>
