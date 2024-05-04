import { Component, Input } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'active-input-field',
  standalone: true,
  imports: [MatIconModule],
  templateUrl: './input-field.component.html',
  styleUrl: './input-field.component.scss',
})
export class InputFieldComponent {
  @Input({ required: true })
  matIcon = '';

  @Input({ required: true })
  type = '';

  @Input({ required: true })
  placeholder = '';
}
