import { Component, Input } from '@angular/core';

@Component({
  selector: 'gains-icon-button',
  templateUrl: './icon-button.component.html'
})
export class IconButtonComponent {

  @Input()
  iconType?: string;

  @Input()
  color: string = '';
}
