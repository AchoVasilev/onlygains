import { Routes } from '@angular/router';
import { CreatePostComponent } from './create-post/create-post.component';
import { CreateExerciseComponent } from './create-exercise/create-exercise.component';

const CONTENT_CREATION_ROUTES: Routes = [
  {
    path: '',
    children: [
      {
        path: 'create/post',
        pathMatch: 'full',
        component: CreatePostComponent,
        title: 'MyActivePal - създай статия'
      },
      {
        path: 'create/exercise',
        pathMatch: 'full',
        component: CreateExerciseComponent,
        title: 'MyActivePal - създай упражнение'
      }
    ]
  }
];

export default CONTENT_CREATION_ROUTES;
