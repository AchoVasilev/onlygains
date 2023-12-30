package com.project.domain.workout

import com.project.domain.BaseEntity
import com.project.infrastructure.converters.StringToListConverter
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import java.util.UUID

@Entity(name = "exercises")
class Exercise protected constructor() : BaseEntity() {
    @Id
    val id: UUID = UUID.randomUUID()
    var name: String? = null
        private set
    var translatedName: String? = null
        private set
    var description: String? = null
        private set
    var imageUrl: String? = null
        private set
    var gifUrl: String? = null
        private set

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(name = "exercises_musclegroups", joinColumns = [JoinColumn(name = "exercise_id")], inverseJoinColumns = [JoinColumn(name = "musclegroup_id")])
     val muscleGroups: MutableSet<MuscleGroup> = mutableSetOf()

    @ManyToMany(mappedBy = "variations")
    val exercises: Set<Exercise> = setOf()

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(name = "exercises_variations", joinColumns = [JoinColumn(name = "exercise_id")], inverseJoinColumns = [JoinColumn(name = "variation_id")])
     val variations: MutableSet<Exercise> = mutableSetOf()

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(name = "exercises_equipment", joinColumns = [JoinColumn(name = "exercise_id")], inverseJoinColumns = [JoinColumn(name = "equipment_id")])
     val equipment: MutableSet<Equipment> = mutableSetOf()

    @Column(name = "main_muscle_groups_ids")
    @Convert(converter = StringToListConverter::class)
     val mainMuscleGroupsIds: MutableList<String> = mutableListOf()

    @Column(name = "synergistic_muscle_groups_ids")
    @Convert(converter = StringToListConverter::class)
     val synergisticMuscleGroupsIds: MutableList<String> = mutableListOf()

    constructor(name: String, translatedName: String, description: String, imageUrl: String, gifUrl: String) : this() {
        this.name = name
        this.translatedName = translatedName
        this.description = description
        this.imageUrl = imageUrl
        this.gifUrl = gifUrl
    }

    fun addVariations(variations: List<Exercise>?) {
        this.variations.addAll(variations!!)
    }

    fun addMuscleGroups(muscleGroups: List<MuscleGroup>?) {
        this.muscleGroups.addAll(muscleGroups!!)
    }

    fun addEquipment(equipment: List<Equipment>?) {
        this.equipment.addAll(equipment!!)
    }

    fun addMainMuscleGroupsIds(ids: List<String>?) {
        mainMuscleGroupsIds.addAll(ids!!)
    }

    fun addSynergisticMuscleGroupIds(ids: List<String>?) {
        synergisticMuscleGroupsIds.addAll(ids!!)
    }
}
