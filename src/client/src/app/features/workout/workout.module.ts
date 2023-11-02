import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WorkoutRoutingModule } from './workout-routing.module';
import { HomeComponent } from './pages/home/home.component';
import { ChartComponent } from './components/chart/chart.component';

@NgModule({
  declarations: [HomeComponent],
  imports: [CommonModule, WorkoutRoutingModule, ChartComponent],
})
export class WorkoutModule {}
