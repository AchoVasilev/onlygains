import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'active-inline-edit',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
  ],
  templateUrl: './inline-edit.component.html',
  styleUrls: ['./inline-edit.component.scss'],
})
export class InlineEditComponent implements OnChanges {

  @Input({ required: true }) value: any;
  @Input({ required: true }) control!: FormControl;
  @Input() type: 'textbox' | 'textarea' = 'textbox';
  @Input() errorMessage?: string = 'Невалидни данни';
  @Input({ required: true }) label?: string = '';

  @Output() update = new EventEmitter<void>();
  @Output() dismiss = new EventEmitter();

  mode: 'view' | 'edit' = 'view';

  get hasValue() {
    return !!this.value;
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.control.patchValue(this.value);
  }

  toggleEditMode() {
    this.mode = 'edit';
  }

  onFocusOut() {
    //check for empty string
    if (this.control?.valid) {
      if (this.control.value === '') {
        this.control.patchValue(null);
      }

      this.mode = 'view';
      this.update.emit();
    }
  }

  onEnterPress(event: Event) {
    const target = event.target as HTMLInputElement;
    target.blur();
  }
}
