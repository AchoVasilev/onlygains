import type { Routes } from '@angular/router';
import { WorkoutDashboardComponent } from './components/workout-dashboard/workout-dashboard.component';
import { WorkoutComponent } from './pages/workout/workout.component';

const WORKOUT_ROUTES: Routes = [
  {
    path: '',
    component: WorkoutComponent,
    children: [
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full',
      },
      {
        path: 'dashboard',
        component: WorkoutDashboardComponent,
        pathMatch: 'full',
        title: 'MyActivePal - тренировъчен профил',
      },
      {
        path: 'calculators',
        children: [
          {
            path: '',
            redirectTo: 'bmi',
            pathMatch: 'full',
          },
          {
            path: 'bmi',
            loadComponent: () =>
              import('./components/calculators/bmi/bmi.component').then(
                c => c.BmiComponent
              ),
            pathMatch: 'full',
            title: 'MyActivePal - BMI',
          },
          {
            path: 'bmr',
            loadComponent: () =>
              import('./components/calculators/bmr/bmr.component').then(
                c => c.BmrComponent
              ),
            pathMatch: 'full',
            title: 'MyActivePal - BMR',
          },
        ],
      },
    ],
  },
];

export default WORKOUT_ROUTES;
