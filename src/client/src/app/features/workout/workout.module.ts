import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {WorkoutRoutingModule} from './workout-routing.module';
import { WorkoutDashboardComponent } from './workout-dashboard/workout-dashboard.component';


@NgModule({
  declarations: [
  
    WorkoutDashboardComponent
  ],
  imports: [
    CommonModule,
    WorkoutRoutingModule
  ]
})
export class WorkoutModule { }
