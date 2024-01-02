import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from 'app/core/services/post/post.service';
import { PostDetailsResource } from 'app/shared/models/post';
import { Observable } from 'rxjs';
import { SideBarComponent } from '../../../shared/components/side-bar/side-bar.component';
import { CommentsComponent } from '../comments/comments.component';
import { NgForTrackByIdDirective } from '../../../shared/directives/ng-for-track-by-id.directive';
import { TagComponent } from '../../../shared/components/tag/tag.component';
import { NgIf, NgFor, AsyncPipe } from '@angular/common';

@Component({
    selector: 'active-post-details',
    templateUrl: './post-details.component.html',
    styleUrls: ['./post-details.component.scss'],
    encapsulation: ViewEncapsulation.None,
    standalone: true,
    imports: [
        NgIf,
        NgFor,
        TagComponent,
        NgForTrackByIdDirective,
        CommentsComponent,
        SideBarComponent,
        AsyncPipe,
    ],
})
export class PostDetailsComponent implements OnInit {
  post$?: Observable<PostDetailsResource>;

  constructor(
    private postService: PostService,
    private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.post$ = this.postService.getById(this.route.snapshot.params['id']);
  }
}
