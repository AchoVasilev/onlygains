import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { EditTodoItemResource, TodoItemDetailsResource } from 'app/shared/models/checklist';

@Component({
  selector: 'active-check-list',
  templateUrl: './check-list.component.html',
  styleUrls: ['./check-list.component.scss'],
})
export class CheckListComponent implements OnInit {
  group = new FormGroup({
    checkListItem: new FormControl<string>('', Validators.required),
  });

  hiddenElements: { [key: string]: boolean } = {};

  @Output() itemChecked = new EventEmitter<string>();
  @Output() itemEdited = new EventEmitter<EditTodoItemResource>();
  @Output() itemDeleted = new EventEmitter<string>();

  @Input({required: true})
  checklistItems: TodoItemDetailsResource[] | null = [];

  ngOnInit(): void {
    this.checklistItems?.forEach((item) => {
      this.hiddenElements[item.id] = false;
    });
  }

  //TODO: emit this as event
  drop(event: CdkDragDrop<TodoItemDetailsResource>) {
    moveItemInArray(
      this.checklistItems!,
      event.previousIndex,
      event.currentIndex
    );
  }

  onChecked(itemId: string, checked: boolean) {
    this.itemChecked.emit(itemId);
  }

  onEdit(itemId: string, itemName: string) {
    this.toggleElementVisibility(itemId);
    this.group.patchValue({ checkListItem: itemName });
  }

  onDelete(itemId: string) {
    this.checklistItems = this.checklistItems!.filter(item => item.id !== itemId);
    this.itemDeleted.emit(itemId);
  }

  onOutsideClick(itemId: string) {
    if (this.hiddenElements[itemId]) {
      this.toggleElementVisibility(itemId);
    }
  }

  onBlur(itemId: string) {
    this.toggleElementVisibility(itemId);
    if (!this.group.controls.checkListItem.pristine && this.group.controls.checkListItem.valid) {
      const {checkListItem} = this.group.value;
      this.itemEdited.emit({name: checkListItem!, id: itemId});
    }
  }

  private toggleElementVisibility(itemId: string) {
    this.hiddenElements[itemId] = !this.hiddenElements[itemId];
  }
}
