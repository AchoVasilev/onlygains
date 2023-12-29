import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WorkoutRoutingModule } from './workout-routing.module';
import { WorkoutDashboardComponent } from './pages/workout-dashboard/workout-dashboard.component';
import { BarChartComponent } from './components/bar-chart/bar-chart.component';
import { SideBarComponent } from 'app/shared/components/side-bar/side-bar.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';

@NgModule({
  declarations: [WorkoutDashboardComponent, UserDetailsComponent],
  imports: [CommonModule, WorkoutRoutingModule, BarChartComponent, SideBarComponent],
})
export class WorkoutModule {}
