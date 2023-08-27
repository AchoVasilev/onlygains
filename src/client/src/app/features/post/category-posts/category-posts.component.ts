import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from 'app/core/services/post/post.service';
import { PostViewResource } from 'app/shared/models/post';
import { Observable, combineLatest, forkJoin, map } from 'rxjs';

@Component({
  selector: 'gains-category-posts',
  templateUrl: './category-posts.component.html',
  styleUrls: ['./category-posts.component.scss'],
})
export class CategoryPostsComponent implements OnInit {
  posts$?: Observable<PostViewResource[]>;
  private categoryName: string = '';
  private categoryId: string = '';
  private currentPage: number = 1;
  itemsPerPage = 30;

  constructor(private route: ActivatedRoute, private postService: PostService) {
    this.categoryName = this.route.snapshot.params['categoryName'];
    this.categoryId = this.route.snapshot.params['categoryId'];
  }

  ngOnInit(): void {
    this.posts$ = this.postService.getPostsBy(
      this.categoryName,
      this.categoryId,
      this.currentPage
    );
  }

  getUrl(categoryName: string, categoryId: string) {
    return `/posts/${categoryName}/${categoryId}`;
  }

  onScroll() {
    this.currentPage++;
    const newPosts$ = this.postService.getPostsBy(this.categoryName, this.categoryId, this.currentPage);
    
    this.posts$ = forkJoin([this.posts$!, newPosts$]).pipe(
      map((arr: any) => arr.reduce((acc: any, curr: any) => acc.concat(curr)))
    );
  }
}
