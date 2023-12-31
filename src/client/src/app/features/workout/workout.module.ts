import { CheckBoxComponent } from './../../shared/components/check-box/check-box.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WorkoutRoutingModule } from './workout-routing.module';
import { WorkoutDashboardComponent } from './pages/workout-dashboard/workout-dashboard.component';
import { BarChartComponent } from './components/bar-chart/bar-chart.component';
import { SideBarComponent } from 'app/shared/components/side-bar/side-bar.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { CheckListComponent } from './components/check-list/check-list.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { CdkDrag, CdkDragHandle, CdkDropList } from '@angular/cdk/drag-drop';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { NgForTrackByIdDirective } from 'app/shared/directives/ng-for-track-by-id.directive';
import { ClickOutsideDirective } from 'app/shared/directives/click-outside.directive';
import { TodoListComponent } from './components/todo-list/todo-list.component';
import { SpinnerComponent } from 'app/shared/components/spinner/spinner.component';
import { CheckListItemComponent } from './components/check-list-item/check-list-item.component';

@NgModule({
  declarations: [
    WorkoutDashboardComponent,
    UserDetailsComponent,
    CheckListComponent,
    TodoListComponent,
    CheckListItemComponent,
  ],
  imports: [
    CommonModule,
    WorkoutRoutingModule,
    BarChartComponent,
    SideBarComponent,
    CheckBoxComponent,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    CdkDropList,
    CdkDrag,
    CdkDragHandle,
    NgForTrackByIdDirective,
    ClickOutsideDirective,
    SpinnerComponent,
  ],
})
export class WorkoutModule {}
