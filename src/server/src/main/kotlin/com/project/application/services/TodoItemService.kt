package com.project.application.services

import com.project.application.models.todoitem.CreateTodoItemResource
import com.project.application.models.todoitem.EditTodoItemResource
import com.project.application.models.todoitem.TodoItemDetailsResource
import com.project.domain.todo.TodoItem
import com.project.infrastructure.data.TodoItemRepository
import com.project.infrastructure.exceptions.exceptions.EntityNotFoundException
import com.project.infrastructure.utilities.LoggerProvider
import io.micronaut.transaction.annotation.ReadOnly
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
open class TodoItemService(private val todoItemRepository: TodoItemRepository) {
    @ReadOnly
    open fun getTodoItems(): List<TodoItemDetailsResource> {
        //TODO: get the user. Return seeded items if user has empty todo list.
        return this.todoItemRepository.getAll()
            .map { TodoItemDetailsResource(it.id, it.name!!, it.isDone) }
    }

    //TODO: If user interacts with any of the seeded items, first add them to his collection.
    @Transactional
    open fun changeDoneStatus(itemId: UUID) : TodoItemDetailsResource {
        var item = this.getItem(itemId)

        item.changeStatus()
        item = this.todoItemRepository.save(item)

        log.info("Successfully changed item done to {}. [itemId={}]", item.isDone, item.id)
        return TodoItemDetailsResource(item.id, item.name!!, item.isDone)
    }

    @Transactional
    open fun addItem(itemResource: CreateTodoItemResource) : TodoItemDetailsResource {
        log.info("Creating new todo item with name {}", itemResource.name)
        var newItem = TodoItem(itemResource.name, null)
        newItem = this.todoItemRepository.save(newItem)

        log.info("Successfully created item. [itemId={}]", newItem.id)

        return TodoItemDetailsResource(newItem.id, newItem.name!!, newItem.isDone)
    }

    @Transactional
    open fun editItem(itemId: UUID, itemResource: EditTodoItemResource) : TodoItemDetailsResource {
        log.info("Editing item. [itemId={}]", itemId)
        var item = this.getItem(itemId)

        item.updateName(itemResource.name)

        item = this.todoItemRepository.save(item)
        log.info("Successfully updated item. [itemId={}]", item.id)

        return TodoItemDetailsResource(item.id, item.name!!, item.isDone)
    }

    @Transactional
    open fun deleteItem(itemId: UUID) {
        log.info("Deleting item. [itemId={}]", itemId)
        val item = this.getItem(itemId)

        item.markAsDeleted()
        this.todoItemRepository.save(item)

        log.info("Successfully deleted item. [itemId={}]", itemId)
    }

    private fun getItem(itemId: UUID) : TodoItem {
        return this.todoItemRepository.findById(itemId)
            .orElseThrow { EntityNotFoundException(TodoItem::class, itemId) }
    }

    private companion object {
        @JvmStatic
        private val log = LoggerProvider.getLogger(TodoItemService::class.java)
    }
}