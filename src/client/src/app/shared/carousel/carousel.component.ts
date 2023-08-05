import { Component, Input } from '@angular/core';
import { CardResource } from '../models/card';

@Component({
  selector: 'gains-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.scss']
})
export class CarouselComponent {
  @Input()
  cards: CardResource[] = [
    {
      id: "asd",
      imageUrl: "/assets/images/initial.jpg",
      title: "some title"
    },
    {
      id: "asd",
      imageUrl: "/assets/images/initial.jpg",
      title: "some title"
    },
    {
      id: "asd",
      imageUrl: "/assets/images/initial.jpg",
      title: "some title"
    },
    {
      id: "asd",
      imageUrl: "/assets/images/initial.jpg",
      title: "some title"
    },
    {
      id: "asd",
      imageUrl: "/assets/images/initial.jpg",
      title: "some title"
    },
  ];
}
