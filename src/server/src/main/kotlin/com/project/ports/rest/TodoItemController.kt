package com.project.ports.rest

import com.project.application.models.todoitem.CreateTodoItemResource
import com.project.application.models.todoitem.EditTodoItemResource
import com.project.application.models.todoitem.TodoItemDetailsResource
import com.project.application.services.TodoItemService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Patch
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import java.util.UUID

@Controller("/todo")
open class TodoItemController(private val todoItemService: TodoItemService) {

    @Get
    open fun getAll(): HttpResponse<List<TodoItemDetailsResource>> {
        return HttpResponse.ok(this.todoItemService.getTodoItems())
    }

    @Patch("/{id}")
    open fun changeDoneStatus(@PathVariable id: UUID): HttpResponse<TodoItemDetailsResource> {
        return HttpResponse.ok(this.todoItemService.changeDoneStatus(id))
    }

    @Put("/{id}")
    open fun edit(@PathVariable id: UUID, @Body item: EditTodoItemResource): HttpResponse<TodoItemDetailsResource> {
        return HttpResponse.ok(this.todoItemService.editItem(id, item))
    }

    @Delete("/{id}")
    open fun delete(@PathVariable id: UUID): HttpResponse<Unit> {
        this.todoItemService.deleteItem(id)

        return HttpResponse.ok()
    }

    @Post
    open fun createItem(@Body item: CreateTodoItemResource): HttpResponse<TodoItemDetailsResource> {
        return HttpResponse.ok(this.todoItemService.addItem(item))
    }
}