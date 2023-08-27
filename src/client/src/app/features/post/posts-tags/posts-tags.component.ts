import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from 'app/core/services/post/post.service';
import { itemsPerPage } from 'app/shared/constants/data-constants';
import { PostViewResource } from 'app/shared/models/post';
import { Observable, forkJoin, map } from 'rxjs';

@Component({
  selector: 'gains-posts-tags',
  templateUrl: './posts-tags.component.html',
  styleUrls: ['./posts-tags.component.scss'],
})
export class PostsTagsComponent {
  private tagId: string = '';

  posts$?: Observable<PostViewResource[]>;

  pageSize = itemsPerPage;
  currentPage = 1;

  constructor(private route: ActivatedRoute, private postService: PostService) {
    this.tagId = this.route.snapshot.params['tagId'];
  }

  ngOnInit(): void {
    this.posts$ = this.postService.getPostsByTagId(this.tagId, this.currentPage);
  }

  getUrl(categoryName: string, categoryId: string) {
    return `/posts/${categoryName}/${categoryId}`;
  }

  onScroll() {
    this.currentPage++;
    const newPosts$ = this.postService.getPostsByTagId(this.tagId, this.currentPage);
    
    this.posts$ = forkJoin([this.posts$!, newPosts$]).pipe(
      map((arr: any) => arr.reduce((acc: any, curr: any) => acc.concat(curr)))
    );
  }
}
