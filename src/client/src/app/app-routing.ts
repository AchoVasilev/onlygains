import type { Routes } from '@angular/router';

export const APPLICATION_ROUTES: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path: 'home',
    loadChildren: () => import('./features/home/home-routes'),
  },
  {
    path: 'posts',
    loadChildren: () => import('./features/post/post-routes'),
  },
  {
    path: 'content',
    loadChildren: () =>
      import('./features/content-creation/content-creation-routes'),
  },
  {
    path: 'exercises',
    loadChildren: () => import('./features/exercise/exercise-routes'),
  },
  {
    path: 'workouts',
    loadChildren: () => import('./features/workout/workout-routes'),
  },
  {
    path: 'calculators',
    loadChildren: () => import('./features/calculators/calculators-routes'),
  },
  {
    path: '**',
    loadComponent: () =>
      import('./shared/components/not-found/not-found.component').then(
        c => c.NotFoundComponent
      ),
  },
];
