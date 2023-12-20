package com.project.messaging.messages.workout;

import com.project.domain.workout.Workout;
import com.project.messaging.messages.ApplicationEventBase;

import java.time.ZonedDateTime;
import java.util.UUID;

public class WorkoutCreated extends ApplicationEventBase {
    private final UUID id;
    private final ZonedDateTime createdAt;
    private final String status;
    private final UUID workoutTemplateId;

    private WorkoutCreated(UUID id, ZonedDateTime createdAt, String status, UUID workoutTemplateId) {
        super();
        this.id = id;
        this.createdAt = createdAt;
        this.status = status;
        this.workoutTemplateId = workoutTemplateId;
    }

    public static WorkoutCreated from(Workout workout) {
        return new WorkoutCreated(workout.id, workout.getCreatedAt(),
                workout.getStatus().name(),
                workout.getWorkoutTemplate() != null ? workout.getWorkoutTemplate().id : null);
    }

    public UUID getId() {
        return this.id;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public String getStatus() {
        return this.status;
    }

    public UUID getWorkoutTemplateId() {
        return this.workoutTemplateId;
    }
}
