package com.project.posts.application.models.tag

import com.project.posts.domain.Tag
import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
data class TagViewResource(val id: UUID?, val name: String?, val translatedName: String?) {
    companion object {
        fun from(tag: Tag): TagViewResource {
            return TagViewResource(tag.id, tag.name, tag.translatedName)
        }
    }
}
