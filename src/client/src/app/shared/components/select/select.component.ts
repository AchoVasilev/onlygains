import { Component, EventEmitter, Input, Output } from '@angular/core';
import type {
  AbstractControl,
  FormControl} from '@angular/forms';
import {
  ReactiveFormsModule,
} from '@angular/forms';
import type { MatSelectChange} from '@angular/material/select';
import { MatSelectModule } from '@angular/material/select';
import type { Selectable } from 'app/shared/models/selectable';

@Component({
  selector: 'active-select',
  standalone: true,
  imports: [ReactiveFormsModule, MatSelectModule],
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.scss'],
})
export class SelectComponent {
  @Input({ required: true })
  label?: string;

  @Input({ required: true })
  selectableItems?: Selectable[] | null;

  @Input({ required: true })
  control!: AbstractControl;

  @Input()
  fullObjectValue: boolean = false;

  @Input()
  multiple: boolean = false;
  st = '';
  @Output()
  selectionChange = new EventEmitter<object>();

  onSelectionChange(ev: MatSelectChange) {
    this.selectionChange.emit(ev.value);
  }

  getInputControl() {
    return this.control as FormControl;
  }
}
