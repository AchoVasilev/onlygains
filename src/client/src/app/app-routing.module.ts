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
    pathMatch: 'full'
  },
  {
    path: 'posts',
    loadChildren: () => import('./features/post/post.module').then(m => m.PostModule)
  }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
