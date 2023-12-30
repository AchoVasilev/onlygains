package com.project.application.models.todoitem

import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotNull
import java.util.UUID

@Serdeable
data class EditTodoItemResource(@NotNull val name: String, @NotNull val id: UUID) {
}