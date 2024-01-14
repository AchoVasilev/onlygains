import { NgClass } from '@angular/common';
import { Component, Input } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'active-icon-button',
  standalone: true,
  imports: [MatButtonModule, MatIconModule, NgClass],
  templateUrl: './icon-button.component.html',
  styleUrls: ['./icon-button.component.scss'],
})
export class IconButtonComponent {
  @Input()
  iconType?: string;

  @Input()
  color: string = 'primary';

  @Input()
  ariaLabel: string = '';

  @Input()
  classStyling?: 'large-icon-button' | 'cursor-all-scroll' | '';
}
