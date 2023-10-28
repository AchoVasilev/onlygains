package com.project.messaging.messages.workout;

import com.project.domain.workout.WorkoutExercise;
import com.project.messaging.messages.ApplicationEventBase;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class WorkoutCreated extends ApplicationEventBase {
    private UUID id;
    private ZonedDateTime createdAt;
    private String status;
    private UUID workoutTemplateId;

    private WorkoutCreated(UUID id, ZonedDateTime createdAt, String status) {
        super();
        this.id = id;
        this.createdAt = createdAt;
        this.status = status;
    }

    public static class Builder {
        private UUID id;
        private ZonedDateTime createdAt;
        private String status;

        public Builder setId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder setCreatedAt(ZonedDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public WorkoutCreated build() {
            return new WorkoutCreated(id, createdAt, status);
        }
    }
}
