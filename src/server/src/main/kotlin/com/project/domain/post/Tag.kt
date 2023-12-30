package com.project.domain.post

import com.project.domain.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import java.util.UUID

@Entity(name = "tags")
class Tag protected constructor() : BaseEntity() {
    @Id
    var id: UUID? = null
        private set
    var name: String? = null
        private set
    var translatedName: String? = null
        private set

    @ManyToMany(mappedBy = "tags")
    private val posts: MutableSet<Post> = HashSet()

    constructor(name: String?, translatedName: String?) : this() {
        this.id = UUID.randomUUID()
        this.name = name
        this.translatedName = translatedName
    }

    fun getPosts(): Set<Post> {
        return posts
    }

    fun removePost(post: Post) {
        posts.remove(post)
    }

    fun addPost(post: Post) {
        posts.add(post)
    }
}
