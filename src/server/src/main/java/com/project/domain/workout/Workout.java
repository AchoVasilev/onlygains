package com.project.domain.workout;

import com.project.domain.BaseEntity;
import com.project.infrastructure.converters.DurationToIntervalConverter;
import com.project.utilities.FindUtil;
import com.project.utilities.Time;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

@Entity(name = "workouts")
public class Workout extends BaseEntity {
    @Id
    private final UUID id;

    @OneToOne
    @JoinColumn(name = "workout_template_id")
    private WorkoutTemplate workoutTemplate;

    private ZonedDateTime finishedAt;

    @Convert(converter = DurationToIntervalConverter.class)
    private Duration duration;

    @Enumerated(EnumType.STRING)
    private WorkoutStatus status;

    protected Workout() {
        super();
        this.id = UUID.randomUUID();
        this.status = WorkoutStatus.Started;
    }

    public UUID getId() {
        return this.id;
    }

    public WorkoutTemplate getWorkoutTemplate() {
        return this.workoutTemplate;
    }

    public ZonedDateTime getFinishedAt() {
        return this.finishedAt;
    }

    public Duration getDuration() {
        return this.duration;
    }

    public WorkoutStatus getStatus() {
        return this.status;
    }

    public static Workout start(WorkoutTemplate workoutTemplate) {
        var workout = new Workout();
        workout.setWorkoutTemplate(workoutTemplate);
        return workout;
    }

    public static Workout startEmptyWorkout() {
        return new Workout();
    }

    public void finish() {
        this.status = WorkoutStatus.Finished;
        this.finishedAt = Time.utcNow();
        this.setDuration();
        this.setModifiedAt(Time.utcNow());
    }

    public void cancel() {
        this.status = WorkoutStatus.Cancelled;
        this.setModifiedAt(Time.utcNow());
    }

    public void addExercise(WorkoutExercise exercise) {
        if (this.workoutTemplate == null) {
            this.workoutTemplate = new WorkoutTemplate();
        }

        this.workoutTemplate.addExercise(exercise);
        this.setModifiedAt(Time.utcNow());
    }

    public void addSetToExercise(UUID exerciseId, double weight, int repetitions) {
        var exercise = this.findExerciseBy(exerciseId);
        exercise.ifPresent(e -> e.addSet(repetitions, weight));
        this.setModifiedAt(Time.utcNow());
    }

    public void updateExercise(UUID exerciseId, UUID setId, double weight, int repetitions) {
        var exercise = this.findExerciseBy(exerciseId);
        exercise.ifPresent(e -> e.updateSet(setId, weight, repetitions));

        this.setModifiedAt(Time.utcNow());
    }

    private Optional<WorkoutExercise> findExerciseBy(UUID exerciseId) {
        Predicate<WorkoutExercise> predicate = e -> e.getExerciseId().equals(exerciseId);
        return FindUtil.findByProperty(this.workoutTemplate.getExercises(), predicate);
    }

    private void setDuration() {
        this.duration = Duration.between(this.getCreatedAt(), this.finishedAt);
    }

    private void setWorkoutTemplate(WorkoutTemplate workoutTemplate) {
        this.workoutTemplate = workoutTemplate;
    }
}
