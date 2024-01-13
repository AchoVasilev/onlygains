import { CategoryService } from 'app/core/services/category/category.service';
import { PostService } from './../../../core/services/post/post.service';
import { Component, OnInit } from '@angular/core';
import { Observable, map } from 'rxjs';
import { CategoryViewResource } from 'app/shared/models/category';
import { CardResource } from 'app/shared/models/card';
import { AsyncPipe } from '@angular/common';
import { CategoriesComponent } from '../categories/categories.component';
import { RecentPostsComponent } from '../recent-posts/recent-posts.component';
import { InspirationalComponent } from '../inspirational/inspirational.component';
import { IntroComponent } from '../intro/intro.component';
import { SpinnerComponent } from 'app/shared/components/spinner/spinner.component';

@Component({
    selector: 'active-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss'],
    animations: [],
    standalone: true,
    imports: [IntroComponent, InspirationalComponent, RecentPostsComponent, CategoriesComponent, AsyncPipe, SpinnerComponent]
})
export class HomeComponent implements OnInit {

  categories$?: Observable<CategoryViewResource[]>;
  posts$?: Observable<CardResource[]>;

  constructor(private postService: PostService, private categoryService: CategoryService) {}

  ngOnInit(): void {
    this.categories$ = this.categoryService.getCategories();
    this.posts$ = this.getNewestPosts();
  }

  getNewestPosts(): Observable<CardResource[]> {
    return this.postService.getNewest().pipe(
      map((posts) => posts.map(p => {
        return {
          id: p.id,
          imageUrl: p.imageUrl,
          title: p.title,
          subtitle: `${p.createdAt, p.createdBy}`,
          text: p.previewText
        }
      }))
    );
  }
}
