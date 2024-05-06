import { Component, Input } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { VALIDATION_ERRORS } from '../../models/validation';

@Component({
  selector: 'active-input-field',
  standalone: true,
  imports: [MatIconModule, ReactiveFormsModule],
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

  @Input({ required: true })
  control!: FormControl<any>;

  evaluateErrors(): string[] {
    if (this.control.invalid && this.control.touched) {
      return Object.keys(this.control.errors!);
    }

    return [];
  }

  mapErrorToMessage(error: string) {
    return VALIDATION_ERRORS.get(error);
  }
}
