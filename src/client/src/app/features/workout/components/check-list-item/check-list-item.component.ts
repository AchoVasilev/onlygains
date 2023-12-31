import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { EditTodoItemResource, TodoItemDetailsResource } from 'app/shared/models/checklist';

@Component({
  selector: 'active-check-list-item',
  templateUrl: './check-list-item.component.html',
  styleUrls: ['./check-list-item.component.scss']
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
