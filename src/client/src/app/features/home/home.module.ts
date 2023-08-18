import { NgModule } from '@angular/core';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home/home.component';
import { SharedModule } from 'app/shared/shared.module';
import { RecentPostsComponent } from './recent-posts/recent-posts.component';
import { RecentPostComponent } from './recent-post/recent-post.component';
import { InspirationalComponent } from './inspirational/inspirational.component';
import { IntroComponent } from './intro/intro.component';
import { CategoriesComponent } from './categories/categories.component';


@NgModule({
  declarations: [
    HomeComponent,
    RecentPostsComponent,
    RecentPostComponent,
    InspirationalComponent,
    IntroComponent,
    CategoriesComponent,
  ],
  imports: [
    HomeRoutingModule,
    SharedModule
  ]
})
export class HomeModule { }
