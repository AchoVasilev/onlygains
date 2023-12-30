package com.project.domain.comment

import com.project.domain.BaseEntity
import com.project.domain.post.Post
import com.project.domain.user.User
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import java.util.UUID

@Entity(name = "comments")
class Comment protected constructor() : BaseEntity() {
    @Id
    val id: UUID = UUID.randomUUID()
    var text: String? = null
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    var post: Post? = null
        private set

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null
        private set

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    val likes: List<Like> = listOf()

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    val dislikes: List<Dislike> = listOf()

    @Column(name = "parent_id")
    var parentId: UUID? = null
        set(parentId) {
            field = parentId
            this.markAsUpdated()
        }

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "parent_id")
    val replies: MutableList<Comment> = mutableListOf()

    constructor(text: String, post: Post, user: User) : this() {
        this.text = text
        this.post = post
        this.user = user
    }

    fun reply(comment: Comment) {
        comment.parentId = id
        replies.add(comment)
        this.markAsUpdated()
    }
}
