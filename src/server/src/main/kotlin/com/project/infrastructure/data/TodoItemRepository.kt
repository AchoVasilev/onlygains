package com.project.infrastructure.data

import com.project.domain.todo.TodoItem
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.UUID

@Repository
interface TodoItemRepository : CrudRepository<TodoItem, UUID> {
    fun findOrderByIsDoneAscModifiedAtAscCreatedAtAsc() : List<TodoItem>
}