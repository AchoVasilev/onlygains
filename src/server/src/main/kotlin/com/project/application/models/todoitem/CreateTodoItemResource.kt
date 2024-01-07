package com.project.application.models.todoitem

import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotNull

@Serdeable
data class CreateTodoItemResource(@NotNull val name: String)