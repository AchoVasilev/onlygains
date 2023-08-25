import { NgModule } from '@angular/core';

import { PostRoutingModule } from './post-routing.module';
import { SharedModule } from 'app/shared/shared.module';
import { CategoryPostsComponent } from './category-posts/category-posts.component';
import { PostDetailsComponent } from './post-details/post-details.component';
import { PostCardComponent } from './post-card/post-card.component';


@NgModule({
  declarations: [
    CategoryPostsComponent,
    PostDetailsComponent,
    PostCardComponent
  ],
  imports: [
    PostRoutingModule,
    SharedModule
  ]
})
export class PostModule { }
