import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CreateTodoItemResource, TodoItemDetailsResource } from 'app/shared/models/checklist';
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

  checkItem(itemId: string) {
    this.http.patch(`${this.apiUrl}/${itemId}`, {}).subscribe();
  }

  createItem(item: CreateTodoItemResource): Observable<TodoItemDetailsResource> {
    return this.http.post<TodoItemDetailsResource>(this.apiUrl, item);
  }
}
