package com.project.infrastructure.data;

import com.project.domain.workout.Equipment;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EquipmentRepository extends CrudRepository<Equipment, UUID> {
    List<Equipment> findByIdIn(List<UUID> ids);
}
