import { Component, Input, OnInit } from '@angular/core';
import { AsyncPipe, NgForOf, NgIf } from '@angular/common';
import { CategoryService } from 'app/core/services/category/category.service';
import { PostService } from 'app/core/services/post/post.service';
import { TagService } from 'app/core/services/tag/tag.service';
import { CategoryViewResource } from 'app/shared/models/category';
import { PostViewResource } from 'app/shared/models/post';
import { TagViewResource } from 'app/shared/models/tag';
import { Observable } from 'rxjs';
import { RouterModule } from '@angular/router';
import { TagComponent } from '../tag/tag.component';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@Component({
  selector: 'gains-side-bar',
  standalone: true,
  imports: [NgIf, NgForOf, AsyncPipe, RouterModule, TagComponent, MatProgressSpinnerModule],
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.scss']
})
export class SideBarComponent implements OnInit{
  categories$?: Observable<CategoryViewResource[]>;
  
  @Input()
  popularPosts$?: Observable<PostViewResource[]>;
  @Input()
  postsTitle: string = 'Популярни теми';

  tags$?: Observable<TagViewResource[]> | null;

  constructor(private categoryService: CategoryService, private postService: PostService, private tagService: TagService) {}

  ngOnInit(): void {
    this.categories$ = this.categoryService.getCategories();
    if (!this.popularPosts$) {
      this.popularPosts$ = this.postService.getPopular();
    }

    this.tags$ = this.tagService.getTags();
  }

  getUrl(categoryName: string, categoryId: string) {
    return `/posts/${categoryName}/${categoryId}`;
  }
}
