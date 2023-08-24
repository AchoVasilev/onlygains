import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from 'app/core/services/post/post.service';
import { PostViewResource } from 'app/shared/models/post';
import { Observable } from 'rxjs';

@Component({
  selector: 'gains-category-posts',
  templateUrl: './category-posts.component.html',
  styleUrls: ['./category-posts.component.scss'],
})
export class CategoryPostsComponent implements OnInit {
  posts$?: Observable<PostViewResource[]>;
  private categoryName: string = '';
  private categoryId: string = '';

  constructor(private route: ActivatedRoute, private postService: PostService) {
    this.categoryName = this.route.snapshot.params['categoryName'];
    this.categoryId = this.route.snapshot.params['categoryId'];
  }

  ngOnInit(): void {
    this.posts$ = this.postService.getPostsBy(
      this.categoryName,
      this.categoryId
    );
  }

  getUrl(categoryName: string, categoryId: string) {
    return `/posts/${categoryName}/${categoryId}`;
  }

}
