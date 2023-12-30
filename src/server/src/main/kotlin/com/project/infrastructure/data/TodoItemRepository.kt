package com.project.infrastructure.data

import com.project.domain.todo.TodoItem
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.UUID

@Repository
interface TodoItemRepository : CrudRepository<TodoItem, UUID> {
    @Query(value = "SELECT * FROM todo_items WHERE is_deleted = FALSE ORDER BY is_done ASC, modified_at ASC, created_at ASC", nativeQuery = true)
    fun getAll() : List<TodoItem>
}