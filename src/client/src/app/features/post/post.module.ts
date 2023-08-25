import { NgModule } from '@angular/core';

import { PostRoutingModule } from './post-routing.module';
import { SharedModule } from 'app/shared/shared.module';
import { CategoryPostsComponent } from './category-posts/category-posts.component';
import { PostDetailsComponent } from './post-details/post-details.component';
import { PostCardComponent } from './post-card/post-card.component';
import { PostsTagsComponent } from './posts-tags/posts-tags.component';


@NgModule({
  declarations: [
    CategoryPostsComponent,
    PostDetailsComponent,
    PostCardComponent,
    PostsTagsComponent
  ],
  imports: [
    PostRoutingModule,
    SharedModule
  ]
})
export class PostModule { }