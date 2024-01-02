import { Component, Input } from '@angular/core';
import { PostViewResource } from 'app/shared/models/post';
import { DateAgoPipe } from '../../../shared/pipes/date-ago/date-ago.pipe';
import { RouterLink } from '@angular/router';
import { TagComponent } from '../../../shared/components/tag/tag.component';
import { NgIf } from '@angular/common';

@Component({
    selector: 'active-post-card',
    templateUrl: './post-card.component.html',
    styleUrls: ['./post-card.component.scss'],
    standalone: true,
    imports: [NgIf, TagComponent, RouterLink, DateAgoPipe]
})
export class PostCardComponent {
  @Input()
  post?: PostViewResource;
}
