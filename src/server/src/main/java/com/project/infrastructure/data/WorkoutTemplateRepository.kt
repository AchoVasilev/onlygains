package com.project.infrastructure.data

import com.project.domain.workout.WorkoutTemplate
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.UUID

@Repository
interface WorkoutTemplateRepository : CrudRepository<WorkoutTemplate, UUID>
