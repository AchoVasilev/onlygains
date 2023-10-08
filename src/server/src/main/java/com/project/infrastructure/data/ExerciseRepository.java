package com.project.infrastructure.data;

import com.project.application.models.exercise.ExerciseResource;
import com.project.domain.workout.Exercise;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, UUID> {
    Optional<Exercise> findByName(String name);

    List<Exercise> findByIdIn(List<UUID> ids);

    @Query(value = """
            SELECT e.id, e.name, e.translated_name, e.image_url FROM public.exercises e
            JOIN exercises_musclegroups em ON e.id = em.exercise_id
            JOIN muscle_groups mg ON mg.id = em.musclegroup_id
            WHERE mg.name ILIKE concat('%', concat(:search, '%')) OR e.name ILIKE concat('%', concat(:search, '%'))
            GROUP BY e.id
            """, nativeQuery = true)
    List<ExerciseResource> findSimilarExercises(String search);
}
