package com.project.application.models.workout;

import com.project.domain.workout.WorkoutSet;
import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;

@Serdeable
public record SetResource(UUID id, int repetitions, double weight) {
    public static SetResource from(WorkoutSet set) {
        return new SetResource(set.getId(), set.getRepetitions(), set.getWeight().getWeight());
    }
}
