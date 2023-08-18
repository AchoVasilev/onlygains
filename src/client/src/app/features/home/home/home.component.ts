import { CategoryService } from 'app/core/services/category/category.service';
import { PostService } from './../../../core/services/post/post.service';
import { Component, OnInit } from '@angular/core';
import { Observable, map } from 'rxjs';
import { CategoryViewResource } from 'app/shared/models/category';
import { PostViewResource } from 'app/shared/models/post';
import { CardResource } from 'app/shared/models/card';

@Component({
  selector: 'gains-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  animations: [
    
  ]
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
          text: p.text
        }
      }))
    )
  }
}
