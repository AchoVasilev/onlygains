import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { Component, Input, QueryList, ViewChildren } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { CheckBoxComponent } from 'app/shared/components/check-box/check-box.component';
import { CheckListDetailsResource } from 'app/shared/models/checklist';

@Component({
  selector: 'active-check-list',
  templateUrl: './check-list.component.html',
  styleUrls: ['./check-list.component.scss']
})
export class CheckListComponent {

  group = new FormGroup({
    checkListItem: new FormControl<string>('')
  });

  @Input()
  checklistItems: CheckListDetailsResource[] = [
    {
      id: 'asd',
      name: 'first'
    }, 
    {
      id: 'fasd',
      name: 'second'
    },
    {
      id: 'aaa',
      name: 'third'
    },
    {
      id: 'bbb',
      name: 'fourth'
    },
    {
      id: 'ccc',
      name: 'fifth'
    },
    {
      id: 'ddd',
      name: 'sixth'
    }
  ]

  //TODO: emit this as event
  drop(event: CdkDragDrop<CheckListDetailsResource>) {
    moveItemInArray(this.checklistItems, event.previousIndex, event.currentIndex);
  }

  onEdit(event: MouseEvent) {
    console.log(event)
  }
}
