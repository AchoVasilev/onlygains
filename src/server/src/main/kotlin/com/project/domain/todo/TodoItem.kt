package com.project.domain.todo

import com.project.common.BaseEntity
import com.project.domain.user.User
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.util.UUID

@Entity(name = "todo_items")
class TodoItem protected constructor() : BaseEntity(){
    @Id
    val id: UUID = UUID.randomUUID()

    var name: String? = null
        private set

    @ManyToOne
    var user: User? = null
        private set

    var isDone: Boolean = false
        private set

    constructor(name: String, user: User?) : this() {
        this.name = name
        this.user = user
    }

    fun changeStatus() {
        this.isDone = !this.isDone
        this.markAsUpdated()
    }

    fun updateName(name: String) {
        this.name = name
        this.markAsUpdated()
    }
}