import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    loadChildren: () => import('./features/home/home.module').then(m => m.HomeModule),
  },
  {
    path: 'posts',
    loadChildren: () => import('./features/post/post.module').then(m => m.PostModule),
  },
  {
    path: 'content',
    loadChildren: () => import('./features/content-creation/content-creation.module').then(m => m.ContentCreationModule),
  },
  {
    path: 'workout',
    loadChildren: () => import('./features/workout/workout.module').then(m => m.WorkoutModule),
  }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
