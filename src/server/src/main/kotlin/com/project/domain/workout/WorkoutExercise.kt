package com.project.domain.workout

import com.project.common.BaseEntity
import com.project.utilities.FindUtil.findByProperty
import com.project.utilities.Time.utcNow
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import java.util.UUID

@Entity(name = "workout_exercises")
class WorkoutExercise protected constructor() : BaseEntity() {
    @Id
    val id: UUID = UUID.randomUUID()

    var exerciseId: UUID? = null
        private set

    var name: String? = null
        private set

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "exercise")
    val sets: MutableList<WorkoutSet> = mutableListOf()

    @ManyToOne(fetch = FetchType.LAZY)
    var workoutTemplate: WorkoutTemplate? = null

    @ManyToOne(fetch = FetchType.LAZY)
    var originalWorkoutTemplate: OriginalWorkoutTemplate? = null
        private set

    private constructor(exercise: Exercise) : this() {
        this.fromExercise(exercise)
        sets.add(WorkoutSet(0.0, 10))
        sets[0].exercise = this
    }

    private constructor(exercise: Exercise, sets: List<WorkoutSet>) : this() {
        this.fromExercise(exercise)
        this.addSets(sets)
    }

    private constructor(exercise: Exercise, workoutTemplate: OriginalWorkoutTemplate?, sets: List<WorkoutSet>) : this() {
        this.fromExercise(exercise)
        this.originalWorkoutTemplate = workoutTemplate
        this.addSets(sets)
    }

    private constructor(exerciseId: UUID?, workoutTemplate: WorkoutTemplate?, sets: List<WorkoutSet>) : this() {
        this.exerciseId = exerciseId
        this.workoutTemplate = workoutTemplate
        this.addSets(sets)
    }

    fun updateSet(setId: UUID, weight: Double, repetitions: Int) {
        val predicate = { ws: WorkoutSet? -> ws?.id == setId }
        val set = findByProperty(this.sets, predicate)
        set?.changeRepetitions(repetitions)
        set?.changeWeight(weight)
    }

    fun addSet(repetitions: Int, weight: Double) {
        val set = WorkoutSet(weight, repetitions)
        set.exercise = this
        sets.add(set)
        this.modifiedAt = utcNow()
    }

    private fun fromExercise(exercise: Exercise) {
        this.exerciseId = exercise.id
        this.name = exercise.name
    }

    private fun addSets(sets: List<WorkoutSet>) {
        sets.forEach { s: WorkoutSet -> s.exercise = this }
        this.sets.addAll(sets)
    }

    companion object {
        @JvmStatic
        fun from(exercise: Exercise): WorkoutExercise {
            return WorkoutExercise(exercise)
        }

        @JvmStatic
        fun from(exercise: Exercise, sets: List<WorkoutSet>): WorkoutExercise {
            return WorkoutExercise(exercise, sets)
        }

        @JvmStatic
        fun from(exercise: Exercise, workoutTemplate: WorkoutTemplate?, sets: List<WorkoutSet>): WorkoutExercise {
            return WorkoutExercise(exercise.id, workoutTemplate, sets)
        }

        @JvmStatic
        fun from(exercise: Exercise, workoutTemplate: OriginalWorkoutTemplate?, sets: List<WorkoutSet>): WorkoutExercise {
            return WorkoutExercise(exercise, workoutTemplate, sets)
        }
    }
}
