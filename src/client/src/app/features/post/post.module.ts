import { NgModule } from '@angular/core';
import { ScrollingModule } from '@angular/cdk/scrolling';

import { PostRoutingModule } from './post-routing.module';
import { CategoryPostsComponent } from './category-posts/category-posts.component';
import { PostDetailsComponent } from './post-details/post-details.component';
import { PostCardComponent } from './post-card/post-card.component';
import { PostsTagsComponent } from './posts-tags/posts-tags.component';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { CommentComponent } from './comment/comment.component';
import { CommentFormComponent } from './comment-form/comment-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CommentsComponent } from './comments/comments.component';
import { IconButtonComponent } from 'app/shared/components/icon-button/icon-button.component';
import { TagComponent } from 'app/shared/components/tag/tag.component';
import { SideBarComponent } from 'app/shared/components/side-bar/side-bar.component';
import { SafeHtmlPipe } from 'app/shared/pipes/safe-html/safe-html.pipe';
import { DateAgoPipe } from 'app/shared/pipes/date-ago/date-ago.pipe';
import { NgForTrackByIdDirective } from 'app/shared/directives/ng-for-track-by-id.directive';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [
    CategoryPostsComponent,
    PostDetailsComponent,
    PostCardComponent,
    PostsTagsComponent,
    CommentComponent,
    CommentFormComponent,
    CommentsComponent,
  ],
  imports: [
    CommonModule,
    PostRoutingModule,
    ScrollingModule,
    InfiniteScrollModule,
    ReactiveFormsModule,
    IconButtonComponent,
    TagComponent,
    SideBarComponent,
    SafeHtmlPipe,
    DateAgoPipe,
    NgForTrackByIdDirective
  ],
})
export class PostModule {}
