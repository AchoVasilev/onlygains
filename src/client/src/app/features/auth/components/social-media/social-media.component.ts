import { Component, Input } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'active-social-media',
  standalone: true,
  imports: [MatIconModule],
  templateUrl: './social-media.component.html',
  styleUrl: './social-media.component.scss',
})
export class SocialMediaComponent {
  @Input({ required: true })
  text: string = '';
  constructor() {}
}
