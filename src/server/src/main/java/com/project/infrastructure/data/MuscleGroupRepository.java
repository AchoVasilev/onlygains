package com.project.infrastructure.data;

import com.project.domain.workout.MuscleGroup;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MuscleGroupRepository extends CrudRepository<MuscleGroup, String> {
    List<MuscleGroup> findByIdIn(List<String> ids);
}
