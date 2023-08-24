import { Component, OnInit } from '@angular/core';
import { CategoryService } from 'app/core/services/category/category.service';
import { PostService } from 'app/core/services/post/post.service';
import { CategoryViewResource } from 'app/shared/models/category';
import { PostViewResource } from 'app/shared/models/post';
import { Observable } from 'rxjs';

@Component({
  selector: 'gains-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.scss']
})
export class SideBarComponent implements OnInit{
  categories$?: Observable<CategoryViewResource[]>;
  popularPosts$?: Observable<PostViewResource[]>;

  constructor(private categoryService: CategoryService, private postService: PostService) {}

  ngOnInit(): void {
    this.categories$ = this.categoryService.getCategories();
    this.popularPosts$ = this.postService.getPopular();
  }

  getUrl(categoryName: string, categoryId: string) {
    return `/posts/${categoryName}/${categoryId}`;
  }
}
