import { Component, Input } from '@angular/core';
import { CardResource } from 'app/shared/shared-module/models/card';

@Component({
  selector: 'gains-recent-posts',
  templateUrl: './recent-posts.component.html',
  styleUrls: ['./recent-posts.component.scss'],
})
export class RecentPostsComponent {
  @Input()
  cardResources?: CardResource[] | null;

  constructor() {}
}
