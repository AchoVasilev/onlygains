import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from 'app/core/services/post/post.service';
import { itemsPerPage } from 'app/shared/constants/data-constants';
import { PostViewResource } from 'app/shared/models/post';
import { SideBarComponent } from '../../../shared/components/side-bar/side-bar.component';
import { PostCardComponent } from '../post-card/post-card.component';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';

@Component({
  selector: 'active-list-posts',
  templateUrl: './list-posts.component.html',
  styleUrls: ['./list-posts.component.scss'],
  standalone: true,
  imports: [InfiniteScrollModule, PostCardComponent, SideBarComponent],
})
export class ListPostsComponent implements OnInit {
  items: PostViewResource[] = [];
  private itemType: string = '';
  private itemId: string = '';
  private currentPage: number = 0;
  private scrolling: boolean = false;
  pageSize: number = itemsPerPage;

  constructor(
    private route: ActivatedRoute,
    private postService: PostService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.itemType = params['itemType'];
      this.itemId = params['itemId'];
      this.getPosts();
    });
  }

  getPosts() {
    if (this.itemType === 'Category') {
      this.postService
        .getPostsBy(this.itemId, this.currentPage, this.pageSize)
        .subscribe(posts =>
          this.scrolling
            ? (this.items = [...this.items, ...posts])
            : (this.items = posts)
        );
    } else if (this.itemType === 'Tag') {
      this.postService
        .getPostsByTagId(this.itemId, this.currentPage, this.pageSize)
        .subscribe(posts =>
          this.scrolling
            ? (this.items = [...this.items, ...posts])
            : (this.items = posts)
        );
    }
  }

  onScroll() {
    if (this.items.length === this.pageSize) {
      this.currentPage++;
      this.scrolling = true;
      this.getPosts();
    }
  }
}
