import { Routes } from '@angular/router';
import { ExerciseDetailsComponent } from './exercise-details/exercise-details.component';

const EXERCISE_ROUTES: Routes = [
  {
    path: '',
    children: [
      {
        path: ':id/:name',
        component: ExerciseDetailsComponent,
        pathMatch: 'full'
      }
    ]
  }
];

export default EXERCISE_ROUTES;