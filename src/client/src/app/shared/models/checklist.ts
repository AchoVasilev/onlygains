export interface TodoItemDetailsResource {
  id: string;
  name: string;
  isDone: boolean;
}

export interface CreateTodoItemResource {
  name: string;
}

export interface EditTodoItemResource {
  id: string;
  name: string;
}
