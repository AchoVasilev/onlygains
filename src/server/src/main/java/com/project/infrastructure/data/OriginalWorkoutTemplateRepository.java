package com.project.infrastructure.data;

import com.project.domain.workout.OriginalWorkoutTemplate;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.UUID;

@Repository
public interface OriginalWorkoutTemplateRepository extends CrudRepository<OriginalWorkoutTemplate, UUID> {
}
