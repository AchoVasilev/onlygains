import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from 'app/core/services/post/post.service';
import { itemsPerPage } from 'app/shared/shared-module/constants/data-constants';
import { PostViewResource } from 'app/shared/shared-module/models/post';

@Component({
  selector: 'gains-category-posts',
  templateUrl: './category-posts.component.html',
  styleUrls: ['./category-posts.component.scss'],
})
export class CategoryPostsComponent implements OnInit {
  items: PostViewResource[] = [];
  private categoryId: string = '';
  private currentPage: number = 0;
  itemsPerPage: number = itemsPerPage;

  constructor(private route: ActivatedRoute, private postService: PostService) {
    this.categoryId = this.route.snapshot.params['categoryId'];
  }

  ngOnInit(): void {
    this.getPosts();
  }

  getPosts() {
    this.postService
      .getPostsBy(
        this.categoryId,
        this.currentPage,
        this.itemsPerPage
      )
      .subscribe((posts) => (this.items = [...this.items, ...posts]));
  }

  getUrl(categoryName: string, categoryId: string) {
    return `/posts/${categoryName}/${categoryId}`;
  }

  onScroll() {
    if (this.items.length === this.itemsPerPage) {
      this.currentPage++;
      this.getPosts();
    }
  }
}
