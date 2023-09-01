import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from 'app/core/services/post/post.service';
import { PostDetailsResource } from 'app/shared/shared-module/models/post';
import { TagViewResource } from 'app/shared/shared-module/models/tag';

@Component({
  selector: 'gains-post-details',
  templateUrl: './post-details.component.html',
  styleUrls: ['./post-details.component.scss']
})
export class PostDetailsComponent implements OnInit {

  post?: PostDetailsResource;
  postId: string;

  constructor(private postService: PostService, private route: ActivatedRoute) {
    this.postId = this.route.snapshot.params['postId'];
  }

  ngOnInit(): void {
    //this.postService.getById(this.postId).subscribe(post => this.post = post);
  }

  getTag(): TagViewResource {
    return {
      name: this.post?.category.name || 'categoryyy',
      id: this.post?.category.id || '',
      translatedName: this.post?.category.translatedName || ''
    }
  }
}
