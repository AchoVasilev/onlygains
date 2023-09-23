package com.project.infrastructure.data;

import com.project.domain.workout.Exercise;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, UUID> {
    Optional<Exercise> findByName(String name);

    List<Exercise> findByIdIn(List<UUID> ids);
}
