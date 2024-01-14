import { Routes } from '@angular/router';
import { WorkoutDashboardComponent } from './pages/workout-dashboard/workout-dashboard.component';
import { WorkoutComponent } from './pages/workout/workout.component';

const WORKOUT_ROUTES: Routes = [
  {
    path: '',
    component: WorkoutComponent,
    children: [
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      },
      {
        path: 'dashboard',
        component: WorkoutDashboardComponent,
        pathMatch: 'full',
        title: 'MyActivePal - тренировъчен профил',
      },
      {
        path: 'bmr',
        loadComponent: () =>
          import('./pages/bmr-page/bmr-page.component').then(
            (c) => c.BmrPageComponent
          ),
      },
    ]
  },
  
];

export default WORKOUT_ROUTES;
