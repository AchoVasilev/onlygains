package com.project.common.enums

import com.fasterxml.jackson.annotation.JsonValue

enum class PostQueryType {
    @JsonValue
    Category,
    @JsonValue
    Tag
}
