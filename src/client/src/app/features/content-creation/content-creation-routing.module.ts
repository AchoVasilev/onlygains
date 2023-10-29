import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreatePostComponent } from './create-post/create-post.component';
import { CreateExerciseComponent } from './create-exercise/create-exercise.component';
import { CreateContentComponent } from './create-content/create-content.component';

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
      },
      {
        path: 'create/content/:type',
        pathMatch: 'full',
        component: CreateContentComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContentCreationRoutingModule { }
