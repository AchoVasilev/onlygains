package com.project.domain.workout

import com.project.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany

@Entity(name = "muscle_groups")
class MuscleGroup protected constructor() : BaseEntity() {
    @Id
    var id: String? = null
        private set

    var name: String? = null
        private set

    var translatedName: String? = null
        private set

    @ManyToMany(mappedBy = "muscleGroups")
    val exercises: Set<Exercise> = setOf()

    constructor(name: String?, translatedName: String?) : this() {
        this.id = translatedName
        this.name = name
        this.translatedName = translatedName
    }
}
