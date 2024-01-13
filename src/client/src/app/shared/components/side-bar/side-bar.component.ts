import { Component, Input, OnInit } from '@angular/core';
import { AsyncPipe } from '@angular/common';
import { CategoryService } from 'app/core/services/category/category.service';
import { PostService } from 'app/core/services/post/post.service';
import { TagService } from 'app/core/services/tag/tag.service';
import { CategoryViewResource } from 'app/shared/models/category';
import { PostViewResource } from 'app/shared/models/post';
import { TagViewResource } from 'app/shared/models/tag';
import { Observable } from 'rxjs';
import { RouterModule } from '@angular/router';
import { TagComponent } from '../tag/tag.component';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { SpinnerComponent } from '../spinner/spinner.component';

@Component({
  selector: 'active-side-bar',
  standalone: true,
  imports: [
    AsyncPipe,
    RouterModule,
    TagComponent,
    MatButtonModule,
    MatIconModule,
    SpinnerComponent
  ],
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.scss'],
})
export class SideBarComponent implements OnInit {
  categories$?: Observable<CategoryViewResource[]>;

  @Input()
  posts$?: Observable<PostViewResource[]>;
  @Input()
  postsTitle: string = 'Популярни теми';
  @Input()
  isWorkout: boolean = false;

  tags$?: Observable<TagViewResource[]> | null;

  constructor(
    private categoryService: CategoryService,
    private postService: PostService,
    private tagService: TagService
  ) {}

  ngOnInit(): void {
    this.categories$ = this.categoryService.getCategories();
    if (!this.posts$) {
      this.posts$ = this.postService.getPopular();
    }

    this.tags$ = this.tagService.getTags();
  }

  getUrl(categoryName: string, categoryId: string) {
    return `/posts/${categoryName}/${categoryId}`;
  }
}
