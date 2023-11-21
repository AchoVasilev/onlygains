import { Component } from '@angular/core';
import { ChartConfiguration } from 'chart.js';

@Component({
  selector: 'gains-workout-dashboard',
  templateUrl: './workout-dashboard.component.html',
  styleUrls: ['./workout-dashboard.component.scss']
})
export class WorkoutDashboardComponent {

  data: ChartConfiguration<'bar'>['data'] = {
    labels: [ '01/06', '07/13', '14/20', '21/27', '28/03' ],
    datasets: [
      { data: [ 1, 0, 1, 0, 1 ], label: 'Тренировки на седмица', backgroundColor: '#3f51b5' },
    ]
  };
}
