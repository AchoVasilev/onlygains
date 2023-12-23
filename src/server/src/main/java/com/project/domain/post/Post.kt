package com.project.domain.post

import com.project.domain.BaseEntity
import com.project.domain.category.Category
import com.project.domain.comment.Comment
import com.project.domain.image.PostImage
import com.project.domain.user.User
import com.project.utilities.Time.utcNow
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import java.util.UUID

@Entity(name = "posts")
class Post protected constructor() : BaseEntity() {
    @Id
    val id: UUID = UUID.randomUUID()
    var title: String? = null
        private set
    var text: String? = null
        private set
    var previewText: String? = null
        private set

    @JvmField
    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: List<Comment> = mutableListOf()

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    val postImages: MutableList<PostImage> = mutableListOf()

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    var category: Category? = null
        private set

    @ManyToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null
        private set

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(name = "posts_tags", joinColumns = [JoinColumn(name = "post_id")], inverseJoinColumns = [JoinColumn(name = "tag_id")])
    val tags: MutableSet<Tag> = mutableSetOf()

    constructor(title: String, text: String, previewText: String, user: User, category: Category) : this() {
        this.title = title
        this.text = text
        this.previewText = previewText
        this.category = category
        this.user = user
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
