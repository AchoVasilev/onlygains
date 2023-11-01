import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreatePostComponent } from './create-post/create-post.component';
import { CreateExerciseComponent } from './create-exercise/create-exercise.component';

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'create/post',
        pathMatch: 'full',
        component: CreatePostComponent
      },
      {
        path: 'create/exercise',
        pathMatch: 'full',
        component: CreateExerciseComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContentCreationRoutingModule { }
