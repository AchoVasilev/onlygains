import { Routes } from '@angular/router';
import { WorkoutDashboardComponent } from './pages/workout-dashboard/workout-dashboard.component';

const WORKOUT_ROUTES: Routes = [
  {
    path: '',
    children: [
      {
        path: 'dashboard',
        component: WorkoutDashboardComponent,
        pathMatch: 'full',
        title: 'MyActivePal - тренировъчен профил',
      },
    ],
  },
];

export default WORKOUT_ROUTES;