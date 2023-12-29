import { Component, Input } from '@angular/core';

@Component({
  selector: 'active-check-box',
  templateUrl: './check-box.component.html',
  styleUrls: ['./check-box.component.scss'],
  standalone: true
})
export class CheckBoxComponent {

  @Input({required: true})
  item!: {name: string, id: string};
}
