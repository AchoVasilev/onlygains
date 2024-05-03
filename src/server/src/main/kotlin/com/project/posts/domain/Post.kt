package com.project.posts.domain

import com.project.common.BaseEntity
import com.project.infrastructure.utilities.Time.utcNow
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.UUID

@Table(name = "posts")
@Entity
class Post protected constructor() : BaseEntity() {
    @Id
    val id: UUID = UUID.randomUUID()
    var title: String? = null
        private set
    var text: String? = null
        private set
    var previewText: String? = null
        private set

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: List<Comment> = mutableListOf()

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    val postImages: MutableList<PostImage> = mutableListOf()

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    var category: Category? = null
        private set

    var userId: UUID? = null

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(name = "posts_tags", joinColumns = [JoinColumn(name = "post_id")], inverseJoinColumns = [JoinColumn(name = "tag_id")])
    val tags: MutableSet<Tag> = mutableSetOf()

    constructor(title: String, text: String, previewText: String, userId: UUID, category: Category) : this() {
        this.title = title
        this.text = text
        this.previewText = previewText
        this.category = category
        this.userId = userId
    }

    fun addImagesToPost(postImages: List<PostImage>?) {
        this.postImages.addAll(postImages!!)
        this.modifiedAt = utcNow()
    }

    fun addImageToPost(postImage: PostImage) {
        postImages.add(postImage)
        this.modifiedAt = utcNow()
    }

    fun addTag(tag: Tag) {
        tags.add(tag)
        tag.addPost(this)
        this.modifiedAt = utcNow()
    }

    fun removeTag(tag: Tag) {
        tags.remove(tag)
        tag.removePost(this)
        this.modifiedAt = utcNow()
    }
}
