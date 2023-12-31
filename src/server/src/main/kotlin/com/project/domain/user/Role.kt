package com.project.domain.user

import com.project.domain.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

@Entity(name = "roles")
class Role protected constructor(): BaseEntity() {
    @Id
    val id: UUID = UUID.randomUUID()
    var name: String? = null
        private set

    constructor(name: String) : this() {
        this.name = name
    }
}
