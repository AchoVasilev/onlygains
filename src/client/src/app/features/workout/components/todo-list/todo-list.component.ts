import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CreateTodoItemResource, TodoItemDetailsResource } from 'app/shared/models/checklist';

@Component({
  selector: 'active-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.scss']
})
export class TodoListComponent {

  @Input({required: true})
  todoItems: TodoItemDetailsResource[] | null = [];

  @Output() itemChecked = new EventEmitter<string>();
  @Output() itemCreated = new EventEmitter<CreateTodoItemResource>();

  shouldShow = false;

  formGroup = new FormGroup({
    name: new FormControl<string>('', [Validators.required])
  });

  onItemChecked(itemId: string) {
    this.itemChecked.emit(itemId);
  }

  onItemCreated(item: CreateTodoItemResource) {
    this.itemCreated.emit(item);
  }

  showForm() {
    this.shouldShow = !this.shouldShow;
  }

  onBlur() {
    this.showForm();
    if (!this.formGroup.controls.name.pristine && this.formGroup.controls.name.valid) {
      const {name} = this.formGroup.value;
      this.itemCreated.emit({name: name!});
    }
  }
}
