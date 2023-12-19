import { Component, Input } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'active-raised-button',
  standalone: true,
  imports: [MatButtonModule],
  templateUrl: './raised-button.component.html',
})
export class RaisedButtonComponent {
  @Input()
  color: string = '';

  @Input()
  type: string = '';

  @Input()
  text: string = '';
}
