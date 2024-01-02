import { Component, Input } from '@angular/core';
import { CardResource } from 'app/shared/models/card';
import { RecentPostComponent } from '../recent-post/recent-post.component';
import { NgIf, NgFor } from '@angular/common';

@Component({
    selector: 'active-recent-posts',
    templateUrl: './recent-posts.component.html',
    styleUrls: ['./recent-posts.component.scss'],
    standalone: true,
    imports: [
        NgIf,
        RecentPostComponent,
        NgFor,
    ],
})
export class RecentPostsComponent {
  @Input()
  cardResources?: CardResource[] | null;

  constructor() {}
}
