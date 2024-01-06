import { Component, Input } from '@angular/core';
import { NgClass, NgIf } from '@angular/common';

@Component({
  selector: 'active-tooltip',
  standalone: true,
  imports: [NgIf, NgClass],
  templateUrl: './tooltip.component.html',
  styleUrls: ['./tooltip.component.scss']
})
export class TooltipComponent {

  @Input({required: true}) text?: string;
  @Input() position?: string = 'top';
}
