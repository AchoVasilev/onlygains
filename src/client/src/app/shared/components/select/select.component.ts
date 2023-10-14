import { NgForOf } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { MatSelectChange, MatSelectModule } from '@angular/material/select';
import { NgForTrackByIdDirective } from 'app/shared/directives/ng-for-track-by-id.directive';
import { Selectable } from 'app/shared/models/selectable';

@Component({
  selector: 'gains-select',
  standalone: true,
  imports: [ReactiveFormsModule, MatSelectModule, NgForOf, NgForTrackByIdDirective],
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
