package com.project.domain.category

import com.project.domain.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

@Entity(name = "categories")
class Category protected constructor() : BaseEntity() {
    @Id
    val id: UUID = UUID.randomUUID()
    var name: String? = null
        private set
    var translatedName: String? = null
        private set
    var imageUrl: String? = null
        private set

    constructor(name: String, translatedName: String, imageUrl: String) : this() {
        this.name = name
        this.translatedName = translatedName
        this.imageUrl = imageUrl
    }
}
