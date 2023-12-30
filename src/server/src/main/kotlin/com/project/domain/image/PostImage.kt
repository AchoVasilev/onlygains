package com.project.domain.image

import com.project.domain.post.Post
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity(name = "post_images")
class PostImage : Image {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    var post: Post? = null
        private set

    protected constructor()

    constructor(url: String?, post: Post?) : super(url) {
        this.post = post
    }
}
