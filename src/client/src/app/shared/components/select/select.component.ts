import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AbstractControl, FormControl, ReactiveFormsModule } from '@angular/forms';
import { MatSelectChange, MatSelectModule } from '@angular/material/select';
import { Selectable } from 'app/shared/models/selectable';

@Component({
  selector: 'active-select',
  standalone: true,
  imports: [ReactiveFormsModule, MatSelectModule],
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.scss'],
})
export class SelectComponent {

  @Input({required: true})
  label?: string;

  @Input({required: true})
  selectableItems?: Selectable[] | null;

  @Input({required: true})
  control!: AbstractControl;

  @Input()
  fullObjectValue: boolean = false;

  @Input()
  multiple: boolean = false;

  @Output()
  selectionChange: EventEmitter<any> = new EventEmitter();

  onSelectionChange(ev: MatSelectChange) {
    this.selectionChange.emit(ev.value);
  }

  getInputControl() {
    return this.control as FormControl;
  }
}
