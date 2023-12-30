export interface TodoItemDetailsResource {
    id: string,
    name: string,
    isDone: boolean
}

export interface CreateTodoItemResource {
    name: string
}