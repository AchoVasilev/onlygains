import { NgModule } from '@angular/core';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home/home.component';
import { RecentPostsComponent } from './recent-posts/recent-posts.component';
import { RecentPostComponent } from './recent-post/recent-post.component';
import { InspirationalComponent } from './inspirational/inspirational.component';
import { IntroComponent } from './intro/intro.component';
import { CategoriesComponent } from './categories/categories.component';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { NgForTrackByIdDirective } from 'app/shared/directives/ng-for-track-by-id.directive';
import { SnakeCasePipe } from 'app/shared/pipes/snake-case/snake-case.pipe';


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
    CommonModule,
    MatCardModule,
    MatIconModule,
    NgForTrackByIdDirective,
    SnakeCasePipe
  ]
})
export class HomeModule { }
