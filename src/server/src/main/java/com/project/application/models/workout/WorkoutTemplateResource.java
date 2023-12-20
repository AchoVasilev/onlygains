package com.project.application.models.workout;

import com.project.domain.workout.WorkoutExercise;
import com.project.domain.workout.WorkoutTemplate;
import io.micronaut.serde.annotation.Serdeable;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Serdeable
public record WorkoutTemplateResource(UUID id, String name, List<WorkoutExerciseDetailsResource> exercises,
                                      List<UUID> workoutIds, ZonedDateTime createdAt) {

    public static WorkoutTemplateResource from(WorkoutTemplate template) {
        return new WorkoutTemplateResource(
                template.id,
                template.getName(),
                template.getExercises().stream().map(WorkoutExerciseDetailsResource::from).toList(),
                template.getExercises().stream().map(WorkoutExercise::getId).toList(),
                template.getCreatedAt());
    }
}
