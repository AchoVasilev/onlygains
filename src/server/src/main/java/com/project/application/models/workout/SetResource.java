package com.project.application.models.workout;

import com.project.domain.workout.Set;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record SetResource(int repetitions, double weight) {
    public static SetResource from(Set set) {
        return new SetResource(set.getRepetitions(), set.getWeight().getWeight());
    }
}
