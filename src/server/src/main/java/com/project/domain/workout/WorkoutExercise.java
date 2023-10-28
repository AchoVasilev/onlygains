package com.project.domain.workout;

import com.project.domain.BaseEntity;
import com.project.utilities.FindUtil;
import com.project.utilities.Time;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Entity(name = "workout_exercises")
public class WorkoutExercise extends BaseEntity {

    @Id
    private final UUID id;

    private UUID exerciseId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "exercise")
    private final List<WorkoutSet> sets;

    @ManyToOne(fetch = FetchType.LAZY)
    private Workout workout;

    @ManyToOne(fetch = FetchType.LAZY)
    private WorkoutTemplate workoutTemplate;

    protected WorkoutExercise() {
        super();
        this.id = UUID.randomUUID();
        this.sets = new ArrayList<>();
    }

    protected WorkoutExercise(UUID exerciseId, Workout workout) {
        this();
        this.exerciseId = exerciseId;
        this.workout = workout;
        this.sets.add(WorkoutSet.from(0, 10));
        this.sets.get(0).setExercise(this);
    }

    protected WorkoutExercise(UUID exerciseId, Workout workout, List<WorkoutSet> sets) {
        this();
        this.exerciseId = exerciseId;
        this.workout = workout;
        sets.forEach(s -> s.setExercise(this));
        this.sets.addAll(sets);
    }

    public UUID getId() {
        return this.id;
    }

    public UUID getExerciseId() {
        return this.exerciseId;
    }

    public List<WorkoutSet> getSets() {
        return this.sets;
    }

    public Workout getWorkout() {
        return this.workout;
    }

    public WorkoutTemplate getWorkoutTemplate() {
        return workoutTemplate;
    }

    public void setWorkoutTemplate(WorkoutTemplate workoutTemplate) {
        this.workoutTemplate = workoutTemplate;
        this.setModifiedAt(Time.utcNow());
    }

    public static WorkoutExercise from(Exercise exercise, Workout workout) {
        return new WorkoutExercise(exercise.getId(), workout);
    }

    public static WorkoutExercise from(Exercise exercise, Workout workout, List<WorkoutSet> sets) {
        return new WorkoutExercise(exercise.getId(), workout, sets);
    }

    public void updateFrom(WorkoutExercise workoutExercise) {
        this.sets.clear();
        this.sets.addAll(workoutExercise.getSets());
        this.setModifiedAt(Time.utcNow());
    }

    public void updateSet(UUID setId, double weight, int repetitions) {
        Predicate<WorkoutSet> predicate = ws -> ws.getId().equals(setId);
        var set = FindUtil.findByProperty(this.sets, predicate);
        set.setWeight(weight);
        set.setRepetitions(repetitions);
    }

    public void addSet(int repetitions, double weight) {
        this.sets.add(WorkoutSet.from(weight, repetitions));
        this.setModifiedAt(Time.utcNow());
    }
}
