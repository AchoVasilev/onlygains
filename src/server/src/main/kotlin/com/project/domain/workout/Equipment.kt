package com.project.domain.workout

import com.project.domain.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import java.util.UUID

@Entity
class Equipment protected constructor() : BaseEntity() {
    @Id
    val id: UUID = UUID.randomUUID()

    var name: String? = null
        private set

    @ManyToMany(mappedBy = "equipment")
    val exercises: Set<Exercise> = setOf()

    constructor(name: String) : this() {
        this.name = name
    }
}
