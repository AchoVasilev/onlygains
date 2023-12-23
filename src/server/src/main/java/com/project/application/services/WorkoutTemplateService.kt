package com.project.application.services

import com.project.domain.workout.WorkoutTemplate
import com.project.infrastructure.data.WorkoutTemplateRepository
import com.project.infrastructure.exceptions.EntityNotFoundException
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
open class WorkoutTemplateService(private val workoutTemplateRepository: WorkoutTemplateRepository) {
    @Transactional(readOnly = true)
    open fun findById(templateId: UUID): WorkoutTemplate {
        return workoutTemplateRepository.findById(templateId)
                .orElseThrow { EntityNotFoundException(WorkoutTemplate::class.java, templateId) }
    }
}
