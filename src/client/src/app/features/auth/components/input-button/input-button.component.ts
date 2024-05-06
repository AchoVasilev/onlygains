import { NgClass } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'active-input-button',
  standalone: true,
  imports: [NgClass],
  templateUrl: './input-button.component.html',
  styleUrl: './input-button.component.scss',
})
export class InputButtonComponent {
  @Input({ required: true })
  type: string = 'submit';

  @Input({ required: true })
  value = 'Login';

  @Input()
  disabled = false;
}
