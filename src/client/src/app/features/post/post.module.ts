import { NgModule } from '@angular/core';

import { PostRoutingModule } from './post-routing.module';
import { SharedModule } from 'app/shared/shared.module';
import { AllPostsComponent } from './all-posts/all-posts.component';
import { PopularPostsComponent } from './popular-posts/popular-posts.component';
import { PostDetailsComponent } from './post-details/post-details.component';


@NgModule({
  declarations: [
    AllPostsComponent,
    PopularPostsComponent,
    PostDetailsComponent
  ],
  imports: [
    PostRoutingModule,
    SharedModule
  ]
})
export class PostModule { }
