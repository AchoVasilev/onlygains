import { OnDestroy, OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { TodoItemService } from 'app/core/services/todo/todo-item.service';
import { WorkoutProfileService } from 'app/core/services/user/workout-profile/workout-profile.service';
import { BodyMassService } from 'app/core/services/workout/body-mass/body-mass.service';
import {
  CreateTodoItemResource,
  EditTodoItemResource,
  TodoItemDetailsResource,
} from 'app/shared/models/checklist';
import {
  UpdateWorkoutProfileResource,
  UserWorkoutProfileDetailsResource,
} from 'app/shared/models/user';
import { ChartConfiguration } from 'chart.js';
import { Observable } from 'rxjs';
import { BehaviorSubject, Subject, map, takeUntil } from 'rxjs';
import { SpinnerComponent } from '../../../../shared/components/spinner/spinner.component';
import { UserDetailsComponent } from '../user-details/user-details.component';
import { BarChartComponent } from '../bar-chart/bar-chart.component';
import { TodoListComponent } from '../todo-list/todo-list.component';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'active-workout-dashboard',
  templateUrl: './workout-dashboard.component.html',
  styleUrls: ['./workout-dashboard.component.scss'],
  standalone: true,
  imports: [
    TodoListComponent,
    BarChartComponent,
    UserDetailsComponent,
    SpinnerComponent,
    AsyncPipe,
  ],
})
export class WorkoutDashboardComponent implements OnInit, OnDestroy {
  private itemSubject = new BehaviorSubject<void>(null!);
  private itemtemObservable$ = this.itemSubject.asObservable();
  private destroy$ = new Subject<void>();

  todoItems$!: Observable<TodoItemDetailsResource[]>;
  user?: UserWorkoutProfileDetailsResource;

  constructor(
    private todoItemService: TodoItemService,
    private bodyMassService: BodyMassService,
    private workoutProfileService: WorkoutProfileService
  ) {}

  ngOnInit(): void {
    this.todoItems$ = this.todoItemService.getAll();

    this.itemtemObservable$.pipe(takeUntil(this.destroy$)).subscribe(() => {
      this.todoItems$ = this.todoItems$.pipe(map(items => items));
    });

    this.getUser();
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

  getUser() {
    this.workoutProfileService
      .getById('af2e8e6a-861b-4b1b-b7d1-81410dbcf1b6')
      .subscribe(user => (this.user = user));
  }

  onItemChecked(itemId: string) {
    this.todoItemService
      .checkItem(itemId)
      .subscribe(() => this.itemSubject.next());
  }

  onItemCreated(item: CreateTodoItemResource) {
    this.todoItemService
      .createItem(item)
      .subscribe(() => this.itemSubject.next());
  }

  onItemEdited(item: EditTodoItemResource) {
    this.todoItemService
      .editItem(item)
      .subscribe(() => this.itemSubject.next());
  }

  onItemDeleted(itemId: string) {
    this.todoItemService
      .deleteItem(itemId)
      .subscribe(() => this.itemSubject.next());
  }

  onCalculateBmi() {
    this.bodyMassService
      .calculateBmi(this.user!.id)
      .subscribe(res => (this.user = res));
  }

  onUpdateProfile(resource: UpdateWorkoutProfileResource) {
    this.workoutProfileService
      .updateProfile(this.user!.id, resource)
      .subscribe(res => (this.user = res));
  }
}
