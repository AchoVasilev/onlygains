package com.project.domain.workout;

import com.project.domain.BaseEntity;
import com.project.domain.post.Tag;
import com.project.domain.valueobjects.Repetitions;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity(name = "exercises")
public class Exercise extends BaseEntity {
    @Id
    private final UUID id;

    private String name;

    private int sets;
    @Embedded
    private final Repetitions repetitions;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "exercises_bodyparts", joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "bodypart_id"))
    private final Set<BodyPart> bodyParts;

    protected Exercise() {
        super();
        this.id = UUID.randomUUID();
        this.bodyParts = new HashSet<>();
        this.sets = 0;
        this.repetitions = Repetitions.from(0);
    }

    public Exercise(String name) {
        this();
        this.name = name;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getSets() {
        return this.sets;
    }

    public Repetitions getRepetitions() {
        return this.repetitions;
    }

    public Set<BodyPart> getBodyParts() {
        return this.bodyParts;
    }

    private void setSets(int sets) {
        this.sets = sets;
    }

    private void changeRepetitions(int reps) {
        this.repetitions.setReps(reps);
    }
}
