import { Component, Input } from '@angular/core';
import { CardResource } from 'app/shared/models/card';

@Component({
  selector: 'active-recent-posts',
  templateUrl: './recent-posts.component.html',
  styleUrls: ['./recent-posts.component.scss'],
})
export class RecentPostsComponent {
  @Input()
  cardResources?: CardResource[] | null;

  constructor() {}
}
