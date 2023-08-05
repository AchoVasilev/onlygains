import { Component, Input } from '@angular/core';
import { CardResource } from '../models/card';

@Component({
  selector: 'gains-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent {

  @Input()
  card?: CardResource;
}
