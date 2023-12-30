package com.project.application.services

import com.project.application.models.exercise.EquipmentResource
import com.project.domain.workout.Equipment
import com.project.infrastructure.data.EquipmentRepository
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
open class EquipmentService(private val equipmentRepository: EquipmentRepository) {
    @Transactional(readOnly = true)
    open fun getBy(ids: List<UUID>): List<Equipment> {
        return equipmentRepository.findByIdIn(ids)
    }

    @Transactional(readOnly = true)
    open fun getAll(): List<EquipmentResource> {
        return equipmentRepository.findAll().map { equipment: Equipment -> EquipmentResource.from(equipment) }
    }
}
