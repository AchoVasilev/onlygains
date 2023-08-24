import { NgModule } from '@angular/core';

import { PostRoutingModule } from './post-routing.module';
import { SharedModule } from 'app/shared/shared.module';
import { CategoryPostsComponent } from './category-posts/category-posts.component';
import { PostDetailsComponent } from './post-details/post-details.component';


@NgModule({
  declarations: [
    CategoryPostsComponent,
    PostDetailsComponent
  ],
  imports: [
    PostRoutingModule,
    SharedModule
  ]
})
export class PostModule { }
