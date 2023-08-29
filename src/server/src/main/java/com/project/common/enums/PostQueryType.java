package com.project.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PostQueryType {
    @JsonValue
    Category,
    @JsonValue
    Tag
}
