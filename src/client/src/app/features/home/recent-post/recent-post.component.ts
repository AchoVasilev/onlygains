import { Component, Input } from '@angular/core';
import { CardResource } from 'app/shared/models/card';

@Component({
  selector: 'gains-recent-post',
  templateUrl: './recent-post.component.html',
  styleUrls: ['./recent-post.component.scss']
})
export class RecentPostComponent {
  @Input()
  card?: CardResource;

  @Input()
  isMain: boolean = false;
}
