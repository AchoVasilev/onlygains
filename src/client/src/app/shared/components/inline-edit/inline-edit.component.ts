import { NgIf } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'active-inline-edit',
  standalone: true,
  imports: [
    NgIf,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
  ],
  templateUrl: './inline-edit.component.html',
  styleUrls: ['./inline-edit.component.scss'],
})
export class InlineEditComponent {
  @Input({ required: true }) value: any;
  @Input({ required: true }) control!: FormControl;
  @Input() type: 'textbox' | 'textarea' = 'textbox';
  @Input() errorMessage?: 'string';
  @Input({ required: true }) label?: string = '';

  @Output() update = new EventEmitter<void>();
  @Output() dismiss = new EventEmitter();

  mode: 'view' | 'edit' = 'view';

  get hasValue() {
    return !!this.value;
  }

  toggleEditMode() {
    this.mode = 'edit';
  }

  onFocusOut() {
    if (this.control?.valid) {
      this.mode = 'view';
      this.update.emit();
    }
  }

  onEnterPress(event: Event) {
    const target = event.target as HTMLInputElement;
    target.blur();
  }
}
