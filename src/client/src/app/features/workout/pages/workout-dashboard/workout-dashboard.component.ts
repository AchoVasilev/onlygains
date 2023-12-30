import { Component, OnDestroy, OnInit } from '@angular/core';
import { TodoItemService } from 'app/core/services/todo/todo-item.service';
import {
  CreateTodoItemResource,
  TodoItemDetailsResource,
} from 'app/shared/models/checklist';
import { ChartConfiguration } from 'chart.js';
import { BehaviorSubject, Observable, Subject, map, takeUntil } from 'rxjs';

@Component({
  selector: 'active-workout-dashboard',
  templateUrl: './workout-dashboard.component.html',
  styleUrls: ['./workout-dashboard.component.scss'],
})
export class WorkoutDashboardComponent implements OnInit, OnDestroy {
  private itemSubject = new BehaviorSubject<TodoItemDetailsResource>(null!);
  private itemtemObservable$ = this.itemSubject.asObservable();
  private destroy$ = new Subject<void>();

  todoItems$!: Observable<TodoItemDetailsResource[]>;

  constructor(private todoItemService: TodoItemService) {}

  ngOnInit(): void {
    this.todoItems$ = this.todoItemService.getAll();

    this.itemtemObservable$.pipe(takeUntil(this.destroy$)).subscribe(() => {
      this.todoItems$ = this.todoItems$.pipe(map((items) => items));
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  data: ChartConfiguration<'bar'>['data'] = {
    labels: ['01/06', '07/13', '14/20', '21/27', '28/03'],
    datasets: [
      {
        data: [1, 0, 1, 0, 1],
        label: 'Тренировки на седмица',
        backgroundColor: '#3f51b5',
      },
    ],
  };

  onItemChecked(itemId: string) {
    this.todoItemService.checkItem(itemId);
  }

  onItemCreated(item: CreateTodoItemResource) {
    this.todoItemService.createItem(item).subscribe((item) => {
      this.itemSubject.next(item);
    });
  }
}
