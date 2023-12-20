package com.project.infrastructure.data

import com.project.domain.workout.Equipment
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.UUID

@Repository
interface EquipmentRepository : CrudRepository<Equipment, UUID> {
    fun findByIdIn(ids: List<UUID?>): List<Equipment>
}
