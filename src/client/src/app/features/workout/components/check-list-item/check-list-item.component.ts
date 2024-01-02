import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormGroup, FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { EditTodoItemResource, TodoItemDetailsResource } from 'app/shared/models/checklist';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { NgIf } from '@angular/common';
import { CdkDragHandle } from '@angular/cdk/drag-drop';
import { IconButtonComponent } from '../../../../shared/components/buttons/icon-button/icon-button.component';
import { CheckBoxComponent } from '../../../../shared/components/check-box/check-box.component';
import { ClickOutsideDirective } from '../../../../shared/directives/click-outside.directive';

@Component({
    selector: 'active-check-list-item',
    templateUrl: './check-list-item.component.html',
    styleUrls: ['./check-list-item.component.scss'],
    standalone: true,
    imports: [ClickOutsideDirective, CheckBoxComponent, IconButtonComponent, CdkDragHandle, NgIf, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatIconModule]
})
export class CheckListItemComponent {

  @Input({required: true}) item!: TodoItemDetailsResource;

  @Output() itemChecked = new EventEmitter<string>();
  @Output() itemEdited = new EventEmitter<EditTodoItemResource>();
  @Output() itemDeleted = new EventEmitter<string>();
  
  showForm = false;
  
  group = new FormGroup({
    checkListItem: new FormControl<string>('', Validators.required),
  });

  onChecked(itemId: string, checked: boolean) {
    this.itemChecked.emit(itemId);
  }

  onEdit(itemName: string) {
    this.toggleElementVisibility();
    this.group.patchValue({ checkListItem: itemName });
  }

  onDelete(itemId: string) {
    this.itemDeleted.emit(itemId);
  }

  onOutsideClick() {
    if (this.showForm) {
      this.toggleElementVisibility();
    }
  }

  onBlur(itemId: string) {
    this.toggleElementVisibility();
    if (!this.group.controls.checkListItem.pristine && this.group.controls.checkListItem.valid) {
      const {checkListItem} = this.group.value;
      this.itemEdited.emit({name: checkListItem!, id: itemId});
    }
  }

  private toggleElementVisibility() {
    this.showForm = !this.showForm;
  }
}
