import { NgIf } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'active-inline-edit',
  standalone: true,
  imports: [NgIf, FormsModule],
  templateUrl: './inline-edit.component.html',
  styleUrls: ['./inline-edit.component.scss'],
})
export class InlineEditComponent implements OnInit {

  @Input({ required: true }) data?: {
    [key: string]: string | number | undefined;
  };

  @Output() submitData = new EventEmitter<EditData>();

  editMode = false;
  inputValue?: string | number;

  get dataValue() {
    return Object.keys(this.data!).map(key => this.data![key])[0];
  }

  ngOnInit(): void {
    this.inputValue = this.dataValue ?? '';
  }

  toggleEditMode() {
    this.editMode = true;
  }

  onFocusOut() {
    this.editMode = false;
    const dataKey = Object.keys(this.data!)[0];
    this.submitData.emit({key: dataKey, value: this.inputValue});
  }

  onEnterPress(event: Event) {
    const target = event.target as HTMLInputElement;
    target.blur();
  }
}

export interface EditData {
  key: string,
  value: number | string | undefined
}