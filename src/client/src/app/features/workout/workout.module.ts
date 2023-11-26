import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WorkoutRoutingModule } from './workout-routing.module';
import { WorkoutDashboardComponent } from './pages/workout-dashboard/workout-dashboard.component';
import { BarChartComponent } from './components/pie-chart/bar-chart.component';

@NgModule({
  declarations: [WorkoutDashboardComponent],
  imports: [CommonModule, WorkoutRoutingModule, BarChartComponent],
})
export class WorkoutModule {}
