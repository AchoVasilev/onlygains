import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from 'app/core/services/post/post.service';
import { itemsPerPage } from 'app/shared/constants/data-constants';
import { PostViewResource } from 'app/shared/models/post';

@Component({
  selector: 'gains-list-posts',
  templateUrl: './list-posts.component.html',
  styleUrls: ['./list-posts.component.scss'],
})
export class ListPostsComponent {
  items: PostViewResource[] = [];
  private itemType: string = '';
  private itemId: string = '';
  private currentPage: number = 0;
  pageSize: number = itemsPerPage;

  constructor(private route: ActivatedRoute, private postService: PostService) {
    this.itemId = this.route.snapshot.params['itemId'];
    this.itemType = this.route.snapshot.params['itemType'];
  }

  ngOnInit(): void {
    this.getPosts();
  }

  getPosts() {
    if (this.itemType === 'categories') {
      this.postService
        .getPostsBy(this.itemId, this.currentPage, this.pageSize)
        .subscribe((posts) => (this.items = [...this.items, ...posts]));
    } else if (this.itemType === 'tags') {
      this.postService
        .getPostsByTagId(this.itemId, this.currentPage, this.pageSize)
        .subscribe((posts) => (this.items = [...this.items, ...posts]));
    }
  }

  onScroll() {
    if (this.items.length === this.pageSize) {
      this.currentPage++;
      this.getPosts();
    }
  }
}
