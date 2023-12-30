package com.project.application.models.todoitem

import io.micronaut.serde.annotation.Serdeable
import java.util.UUID

@Serdeable
data class TodoItemDetailsResource(val id: UUID, val name: String, val isDone: Boolean)
