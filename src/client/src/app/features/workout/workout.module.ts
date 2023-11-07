import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WorkoutRoutingModule } from './workout-routing.module';
import { ChartComponent } from './components/chart/chart.component';
import { WorkoutDashboardComponent } from './pages/workout-dashboard/workout-dashboard.component';

@NgModule({
  declarations: [
    WorkoutDashboardComponent
  ],
  imports: [CommonModule, WorkoutRoutingModule, ChartComponent],
})
export class WorkoutModule {}
