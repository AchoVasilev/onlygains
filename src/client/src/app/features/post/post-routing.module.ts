import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoryPostsComponent } from './category-posts/category-posts.component';
import { PostDetailsComponent } from './post-details/post-details.component';
import { PostsTagsComponent } from './posts-tags/posts-tags.component';

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: ':categoryName/:categoryId',
        component: CategoryPostsComponent,
        pathMatch: 'full'
      },
      {
        path: 'details/:id/:title',
        component: PostDetailsComponent,
        pathMatch: 'full'
      },
      {
        path: 'tags/:tagName/:tagId',
        component: PostsTagsComponent,
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
