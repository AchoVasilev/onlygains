package com.project.posts.domain

import com.project.common.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.UUID

@Table(name = "categories")
@Entity
class Category protected constructor() : BaseEntity() {
    @Id
    val id: UUID = UUID.randomUUID()
    var name: String? = null
        private set
    var translatedName: String? = null
        private set
    var imageUrl: String? = null
        private set

    @OneToMany(mappedBy = "category")
    val posts: List<Post> = mutableListOf()

    constructor(name: String, translatedName: String, imageUrl: String) : this() {
        this.name = name
        this.translatedName = translatedName
        this.imageUrl = imageUrl
    }
}
