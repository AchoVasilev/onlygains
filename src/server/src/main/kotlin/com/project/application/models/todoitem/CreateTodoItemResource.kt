package com.project.application.models.todoitem

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CreateTodoItemResource(val name: String)