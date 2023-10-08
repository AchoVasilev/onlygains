package com.project.application.services;

import com.project.application.models.exercise.EquipmentResource;
import com.project.domain.workout.Equipment;
import com.project.infrastructure.data.EquipmentRepository;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.UUID;

@Singleton
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Transactional(readOnly = true)
    public List<Equipment> getBy(List<UUID> ids) {
        return this.equipmentRepository.findByIdIn(ids);
    }

    @Transactional(readOnly = true)
    public List<EquipmentResource> getAll() {
        return this.equipmentRepository.findAll().stream().map(EquipmentResource::from).toList();
    }
}
