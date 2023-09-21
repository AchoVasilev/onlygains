package com.project.application.models.exercise;

import com.project.domain.workout.Equipment;
import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;

@Serdeable
public record EquipmentResource(UUID id, String name) {
    public static EquipmentResource from(Equipment equipment) {
        return new EquipmentResource(equipment.getId(), equipment.getName());
    }
}
