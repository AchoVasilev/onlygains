import {
  trigger,
  state,
  style,
  transition,
  animate,
  query,
  animateChild,
  stagger,
} from '@angular/animations';
import { CdkDragDrop, moveItemInArray, transferArrayItem, CdkDropList, CdkDrag } from '@angular/cdk/drag-drop';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import {
  EditTodoItemResource,
  TodoItemDetailsResource,
} from 'app/shared/models/checklist';
import { IconButtonComponent } from '../../../../shared/components/buttons/icon-button/icon-button.component';
import { CheckListItemComponent } from '../check-list-item/check-list-item.component';
import { NgFor, NgIf } from '@angular/common';

@Component({
    selector: 'active-check-list',
    templateUrl: './check-list.component.html',
    styleUrls: ['./check-list.component.scss'],
    animations: [
        trigger('items', [
            transition(':enter', [
                style({ opacity: 0 }),
                animate('700ms ease-out', style({ opacity: 1 })), // final
            ]),
            transition(':leave', [
                style({ opacity: 1, height: '*' }),
                animate('700ms ease-in', style({
                    opacity: 0,
                    height: '0px',
                    margin: '0px',
                })),
            ]),
        ]),
        trigger('rotate', [
            state('default', style({ transform: 'rotate(0)' })),
            state('rotated', style({ transform: 'rotate(180deg' })),
            transition('default<=>rotated', animate('200ms')),
        ]),
        trigger('list', [
            transition(':enter', [
                query('@items', stagger(100, animateChild()), { optional: true }),
            ]),
        ]),
    ],
    standalone: true,
    imports: [
        CdkDropList,
        NgFor,
        CdkDrag,
        CheckListItemComponent,
        NgIf,
        IconButtonComponent,
    ],
})
export class CheckListComponent implements OnInit {
  @Output() itemChecked = new EventEmitter<string>();
  @Output() itemEdited = new EventEmitter<EditTodoItemResource>();
  @Output() itemDeleted = new EventEmitter<string>();

  @Input({ required: true })
  checklistItems: TodoItemDetailsResource[] | null = [];

  visibleItems: TodoItemDetailsResource[] = [];
  hiddenItems: TodoItemDetailsResource[] = [];
  isExpanded = false;

  ngOnInit(): void {
    this.visibleItems = this.checklistItems?.slice(0, 10) || [];
    this.hiddenItems = this.checklistItems?.slice(10) || [];
  }

  //TODO: emit this as event
  drop(event: CdkDragDrop<TodoItemDetailsResource[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    }
  }

  onChecked(itemId: string) {
    this.itemChecked.emit(itemId);
  }

  onEdit(item: EditTodoItemResource) {
    this.itemEdited.emit({ name: item.name, id: item.id });
  }

  onDelete(itemId: string) {
    this.itemDeleted.emit(itemId);
  }

  toggleExpand() {
    this.isExpanded = !this.isExpanded;
  }
}
