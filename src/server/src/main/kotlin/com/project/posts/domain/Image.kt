package com.project.posts.domain

import com.project.common.BaseEntity
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.util.UUID

@MappedSuperclass
abstract class Image protected constructor() : BaseEntity() {
    @Id
    val id: UUID = UUID.randomUUID()
    var url: String? = null
        private set


    protected constructor(url: String) : this() {
        this.url = url
    }
}
