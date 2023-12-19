import { Component, Input } from '@angular/core';
import { PostViewResource } from 'app/shared/models/post';

@Component({
  selector: 'active-post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.scss']
})
export class PostCardComponent {
  @Input()
  post?: PostViewResource;
}
