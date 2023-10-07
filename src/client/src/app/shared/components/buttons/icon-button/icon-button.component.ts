import { Component, Input } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'gains-icon-button',
  standalone: true,
  imports: [MatButtonModule, MatIconModule],
  templateUrl: './icon-button.component.html'
})
export class IconButtonComponent {
  @Input()
  iconType?: string;

  @Input()
  color: string = '';
}
