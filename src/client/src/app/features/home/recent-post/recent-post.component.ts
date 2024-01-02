import { Component, Input } from '@angular/core';
import { CardResource } from 'app/shared/models/card';
import { SnakeCasePipe } from '../../../shared/pipes/snake-case/snake-case.pipe';
import { RouterLink } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { NgIf } from '@angular/common';

@Component({
    selector: 'active-recent-post',
    templateUrl: './recent-post.component.html',
    styleUrls: ['./recent-post.component.scss'],
    standalone: true,
    imports: [NgIf, MatCardModule, RouterLink, SnakeCasePipe]
})
export class RecentPostComponent {
  @Input()
  card?: CardResource;

  @Input()
  isMain: boolean = false;
}
