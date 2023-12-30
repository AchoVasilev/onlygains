import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'active-check-box',
  templateUrl: './check-box.component.html',
  styleUrls: ['./check-box.component.scss'],
  standalone: true
})
export class CheckBoxComponent {

  @Output() checked = new EventEmitter<boolean>();
  
  @Input({required: true})
  item!: {name: string, id: string, isDone: boolean};

  onChange(event: Event) {
    const eventTarget = event.target as HTMLInputElement;
    this.checked.emit(eventTarget.checked);
  }
}
