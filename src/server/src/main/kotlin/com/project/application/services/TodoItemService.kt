package com.project.application.services

import com.project.application.models.todoitem.CreateTodoItemResource
import com.project.application.models.todoitem.TodoItemDetailsResource
import com.project.domain.todo.TodoItem
import com.project.infrastructure.data.TodoItemRepository
import com.project.infrastructure.exceptions.EntityNotFoundException
import io.micronaut.transaction.annotation.ReadOnly
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
open class TodoItemService(private val todoItemRepository: TodoItemRepository) {
    @ReadOnly
    open fun getTodoItems(): List<TodoItemDetailsResource> {
        //TODO: get the user. Return seeded items if user has empty todo list.
        return this.todoItemRepository.findOrderByIsDoneAscModifiedAtAscCreatedAtAsc()
            .map { TodoItemDetailsResource(it.id, it.name!!, it.isDone) }
    }

    @Transactional
    open fun changeDoneStatus(itemId: UUID) {
        val item = this.todoItemRepository.findById(itemId)
            .orElseThrow { EntityNotFoundException(TodoItem::class, itemId) }

        item.changeStatus()
        this.todoItemRepository.save(item)

        log.info("Successfully changed item done to {}. [itemId={}]", item.isDone, item.id)
    }

    @Transactional
    open fun addItem(itemResource: CreateTodoItemResource) : TodoItemDetailsResource {
        log.info("Creating new todo item with name {}", itemResource.name)
        var newItem = TodoItem(itemResource.name, null)
        newItem = this.todoItemRepository.save(newItem)

        log.info("Successfully created item. [itemId={}]", newItem.id)

        return TodoItemDetailsResource(newItem.id, newItem.name!!, newItem.isDone)
    }

    private companion object {
        @JvmStatic
        private val log = LoggerProvider.getLogger(TodoItemService::class.java)
    }
}