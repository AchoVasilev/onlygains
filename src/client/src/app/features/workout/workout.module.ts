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
import { CdkDrag, CdkDragHandle, CdkDropList } from '@angular/cdk/drag-drop';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { NgForTrackByIdDirective } from 'app/shared/directives/ng-for-track-by-id.directive';
import { ClickOutsideDirective } from 'app/shared/directives/click-outside.directive';
import { TodoListComponent } from './components/todo-list/todo-list.component';
import { SpinnerComponent } from 'app/shared/components/spinner/spinner.component';
import { CheckListItemComponent } from './components/check-list-item/check-list-item.component';
import { IconButtonComponent } from 'app/shared/components/buttons/icon-button/icon-button.component';
import { BmiDialogComponent } from './components/bmi-dialog/bmi-dialog.component';
import { MatDialogModule } from '@angular/material/dialog';

@NgModule({
  declarations: [
    WorkoutDashboardComponent,
    UserDetailsComponent,
    CheckListComponent,
    TodoListComponent,
    CheckListItemComponent,
    BmiDialogComponent,
  ],
  imports: [
    CommonModule,
    WorkoutRoutingModule,
    BarChartComponent,
    SideBarComponent,
    CheckBoxComponent,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatDialogModule,
    ReactiveFormsModule,
    CdkDropList,
    CdkDrag,
    CdkDragHandle,
    NgForTrackByIdDirective,
    ClickOutsideDirective,
    SpinnerComponent,
    IconButtonComponent
  ],
})
export class WorkoutModule {}
