package com.project.domain.workout;

import com.project.domain.BaseEntity;
import com.project.infrastructure.converters.DurationToIntervalConverter;
import com.project.utilities.Time;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.Type;
import org.hibernate.dialect.PostgreSQLIntervalSecondJdbcType;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity(name = "workouts")
public class Workout extends BaseEntity {
    @Id
    private final UUID id;

    @ManyToOne
    @JoinColumn(name = "workout_template_id")
    private WorkoutTemplate workoutTemplate;

    @ManyToMany(mappedBy = "workouts")
    private final Set<Exercise> exercises;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_history_id")
    private WorkoutHistory workoutHistory;

    private ZonedDateTime finishedAt;

    @Convert(converter = DurationToIntervalConverter.class)
    private Duration duration;

    protected Workout() {
        super();
        this.id = UUID.randomUUID();
        this.exercises = new HashSet<>();
    }

    public UUID getId() {
        return this.id;
    }

    public WorkoutTemplate getWorkoutTemplate() {
        return this.workoutTemplate;
    }

    public Set<Exercise> getExercises() {
        return this.exercises;
    }

    public ZonedDateTime getFinishedAt() {
        return this.finishedAt;
    }

    public Duration getDuration() {
        return this.duration;
    }

    public static Workout startEmptyWorkout(WorkoutTemplate workoutTemplate, List<Exercise> additionalExercises) {
        var workout = new Workout();
        workout.addWorkoutTemplate(workoutTemplate);
        workout.addExercises(additionalExercises);

        return workout;
    }

    public static Workout startEmptyWorkout() {
        return new Workout();
    }

    public void finish(WorkoutTemplate workoutTemplate, List<Exercise> additionalExercises) {
        this.workoutHistory.addWorkout(this);
        this.workoutTemplate = workoutTemplate;
        workoutTemplate.addWorkout(this);

        if (!this.exercises.isEmpty()) {
            this.exercises.clear();
        }

        this.addExercises(additionalExercises);

        this.finishedAt = Time.utcNow();
        this.setDuration();
        this.setModifiedAt(Time.utcNow());
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
        this.setModifiedAt(Time.utcNow());
    }

    private void setDuration() {
        this.duration = Duration.between(this.getCreatedAt(), this.finishedAt);
    }

    private void addWorkoutTemplate(WorkoutTemplate workoutTemplate) {
        this.workoutTemplate = workoutTemplate;
    }

    private void addExercises(List<Exercise> exercises) {
        this.exercises.addAll(exercises);
    }
}
