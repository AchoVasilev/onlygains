import { NgModule } from '@angular/core';
import { ScrollingModule } from '@angular/cdk/scrolling';

import { PostRoutingModule } from './post-routing.module';
import { SharedModule } from 'app/shared/shared-module/shared.module';
import { CategoryPostsComponent } from './category-posts/category-posts.component';
import { PostDetailsComponent } from './post-details/post-details.component';
import { PostCardComponent } from './post-card/post-card.component';
import { PostsTagsComponent } from './posts-tags/posts-tags.component';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { CommentComponent } from './comment/comment.component';
import { CommentFormComponent } from './comment-form/comment-form.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    CategoryPostsComponent,
    PostDetailsComponent,
    PostCardComponent,
    PostsTagsComponent,
    CommentComponent,
    CommentFormComponent,
  ],
  imports: [
    PostRoutingModule,
    SharedModule,
    ScrollingModule,
    InfiniteScrollModule,
    ReactiveFormsModule
  ],
})
export class PostModule {}
