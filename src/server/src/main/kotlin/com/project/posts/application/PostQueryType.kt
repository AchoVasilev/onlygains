package com.project.posts.application

import com.fasterxml.jackson.annotation.JsonValue

enum class PostQueryType {
    @JsonValue
    Category,
    @JsonValue
    Tag
}
