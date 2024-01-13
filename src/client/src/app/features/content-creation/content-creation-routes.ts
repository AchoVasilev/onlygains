import { Routes } from '@angular/router';

const CONTENT_CREATION_ROUTES: Routes = [
  {
    path: 'create/post',
    pathMatch: 'full',
    loadComponent: () => import('./create-post/create-post.component').then(c => c.CreatePostComponent),
    title: 'MyActivePal - създай статия',
  },
  {
    path: 'create/exercise',
    pathMatch: 'full',
    loadComponent: () => import('./create-exercise/create-exercise.component').then(c => c.CreateExerciseComponent),
    title: 'MyActivePal - създай упражнение',
  },
];

export default CONTENT_CREATION_ROUTES;
