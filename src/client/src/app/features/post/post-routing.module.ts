import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PostDetailsComponent } from './post-details/post-details.component';
import { ListPostsComponent } from './list-posts/list-posts.component';

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'details/:id/:title',
        component: PostDetailsComponent,
        pathMatch: 'full'
      },
      {
        path: 'list/:itemType/:itemName/:itemId',
        component: ListPostsComponent,
        pathMatch: 'full'
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PostRoutingModule { }
