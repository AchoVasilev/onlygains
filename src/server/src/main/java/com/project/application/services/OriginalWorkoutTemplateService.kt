package com.project.application.services

import com.project.domain.workout.OriginalWorkoutTemplate
import com.project.infrastructure.data.OriginalWorkoutTemplateRepository
import com.project.infrastructure.exceptions.EntityNotFoundException
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
open class OriginalWorkoutTemplateService(private val originalWorkoutTemplateRepository: OriginalWorkoutTemplateRepository) {
    open fun findBy(id: UUID): OriginalWorkoutTemplate {
        return originalWorkoutTemplateRepository.findById(id)
                .orElseThrow { EntityNotFoundException(OriginalWorkoutTemplate::class.java, id) }
    }
}
