package com.project.application.models.exercise

import com.project.domain.workout.Equipment
import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
data class EquipmentResource(val id: UUID, val name: String?) {
    companion object {
        fun from(equipment: Equipment): EquipmentResource {
            return EquipmentResource(equipment.id, equipment.name)
        }
    }
}
