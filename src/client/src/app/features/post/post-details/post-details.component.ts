import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { PostService } from 'app/core/services/post/post.service';
import { PostDetailsResource } from 'app/shared/models/post';
import { Observable } from 'rxjs';

@Component({
  selector: 'gains-post-details',
  templateUrl: './post-details.component.html',
  styleUrls: ['./post-details.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class PostDetailsComponent implements OnInit {
  post$?: Observable<PostDetailsResource>;

  constructor(
    private postService: PostService,
    private route: ActivatedRoute,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {
    this.post$ = this.postService.getById(this.route.snapshot.params['id']);
  }

  sanitize(text: string) {
    return this.sanitizer.bypassSecurityTrustHtml(text);
  }
}
