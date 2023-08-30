import { NgModule } from '@angular/core';
import { ScrollingModule } from '@angular/cdk/scrolling';

import { PostRoutingModule } from './post-routing.module';
import { SharedModule } from 'app/shared/shared-module/shared.module';
import { CategoryPostsComponent } from './category-posts/category-posts.component';
import { PostDetailsComponent } from './post-details/post-details.component';
import { PostCardComponent } from './post-card/post-card.component';
import { PostsTagsComponent } from './posts-tags/posts-tags.component';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { CreatePostComponent } from './create-post/create-post.component';
import { TextEditorModule } from 'app/shared/text-editor/text-editor.module';

@NgModule({
  declarations: [
    CategoryPostsComponent,
    PostDetailsComponent,
    PostCardComponent,
    PostsTagsComponent,
    CreatePostComponent,
  ],
  imports: [
    PostRoutingModule,
    SharedModule,
    ScrollingModule,
    InfiniteScrollModule,
    TextEditorModule
  ],
})
export class PostModule {}
