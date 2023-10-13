import { NgForOf } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output, inject } from '@angular/core';
import { ControlContainer, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatSelectChange, MatSelectModule } from '@angular/material/select';
import { Selectable } from 'app/shared/models/selectable';

@Component({
  selector: 'gains-select',
  standalone: true,
  imports: [ReactiveFormsModule, MatSelectModule, NgForOf],
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.scss'],
})
export class SelectComponent {

  @Input({required: true})
  label?: string;

  @Input({required: true})
  selectableItems?: Selectable[] | null;

  @Input({required: true})
  control!: FormControl;

  @Input()
  fullObjectValue: boolean = false;

  @Input()
  multiple: boolean = false;

  @Output()
  selectionChange: EventEmitter<any> = new EventEmitter();

  onSelectionChange(ev: MatSelectChange) {
    this.selectionChange.emit(ev.value);
  }
}
