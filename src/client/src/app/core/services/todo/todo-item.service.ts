import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CreateTodoItemResource, EditTodoItemResource, TodoItemDetailsResource } from 'app/shared/models/checklist';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TodoItemService {
  private apiUrl = environment.apiUrl + '/todo';

  constructor(private http: HttpClient) {}

  getAll(): Observable<TodoItemDetailsResource[]> {
    return this.http.get<TodoItemDetailsResource[]>(this.apiUrl);
  }

  checkItem(itemId: string): Observable<TodoItemDetailsResource> {
    return this.http.patch<TodoItemDetailsResource>(`${this.apiUrl}/${itemId}`, {});
  }

  createItem(item: CreateTodoItemResource): Observable<TodoItemDetailsResource> {
    return this.http.post<TodoItemDetailsResource>(this.apiUrl, item);
  }

  editItem(item: EditTodoItemResource): Observable<TodoItemDetailsResource> {
    return this.http.put<TodoItemDetailsResource>(`${this.apiUrl}/${item.id}`, item)
  }

  deleteItem(id: string) : Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
