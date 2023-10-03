import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from 'app/core/services/post/post.service';
import { itemsPerPage } from 'app/shared/constants/data-constants';
import { PostViewResource } from 'app/shared/models/post';

@Component({
  selector: 'gains-posts-tags',
  templateUrl: './posts-tags.component.html',
  styleUrls: ['./posts-tags.component.scss'],
})
export class PostsTagsComponent {
  private tagId: string = '';

  items: PostViewResource[] = [];
  pageSize = itemsPerPage;
  currentPage = 0;

  constructor(private route: ActivatedRoute, private postService: PostService) {
    this.tagId = this.route.snapshot.params['tagId'];
  }

  ngOnInit(): void {
    this.getPosts();
  }

  getPosts() {
    this.postService.getPostsByTagId(this.tagId, this.currentPage, this.pageSize).subscribe((posts) => (this.items = [...this.items, ...posts]));
  }

  getUrl(categoryName: string, categoryId: string) {
    return `/posts/${categoryName}/${categoryId}`;
  }

  onScroll() {
    if (this.items.length === this.pageSize) {
      this.currentPage++;
      this.getPosts();
    }
  }
}
