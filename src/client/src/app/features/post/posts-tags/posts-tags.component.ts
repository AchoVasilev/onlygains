import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from 'app/core/services/post/post.service';
import { PostViewResource } from 'app/shared/models/post';
import { Observable } from 'rxjs';

@Component({
  selector: 'gains-posts-tags',
  templateUrl: './posts-tags.component.html',
  styleUrls: ['./posts-tags.component.scss'],
})
export class PostsTagsComponent {
  posts$?: Observable<PostViewResource[]>;
  private tagId: string = '';

  constructor(private route: ActivatedRoute, private postService: PostService) {
    this.tagId = this.route.snapshot.params['tagId'];
  }

  ngOnInit(): void {
    this.posts$ = this.postService.getPostsByTagId(this.tagId);
  }

  getUrl(categoryName: string, categoryId: string) {
    return `/posts/${categoryName}/${categoryId}`;
  }
}
