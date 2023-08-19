import { Component, Input } from '@angular/core';
import { CardResource } from 'app/shared/models/card';

@Component({
  selector: 'gains-recent-posts',
  templateUrl: './recent-posts.component.html',
  styleUrls: ['./recent-posts.component.scss'],
})
export class RecentPostsComponent {
  @Input()
  cardResources?: CardResource[] | null;

  card: CardResource = {
    id: 'asdasd',
    imageUrl: '/assets/images/initial.jpg',
    title: 'The best way to make Online Business',
    subtitle: '14 May 2016, 10 comments, David Walker',
    text: 'Lorem ipsum dolor sit amet, maecenas eget vestibulum justo imperdiet, wisi risus purus augue vulputate voluptate neque, curabitur dolor libero sodales vitae elit massa. Lorem ipsum dolor sit amet, maecenas eget vestibulum justo imperdiet. Lorem ipsum dolor sit amet, maecenas eget vestibulum justo imperdiet, wisi risus purus augue vulputate voluptate neque, curabitur dolor libero sodales vitae elit massa. Lorem ipsum dolor sit amet, maecenas eget vestibulum justo imperdiet. Lorem ipsum dolor sit amet, maecenas eget vestibulum justo imperdiet, wisi risus purus augue vulputate voluptate neque, curabitur dolor libero sodales vitae elit massa. Lorem ipsum dolor sit amet, maecenas eget vestibulum justo imperdiet. Lorem ipsum dolor sit amet, maecenas eget vestibulum justo imperdiet, wisi risus purus augue vulputate voluptate neque, curabitur dolor libero sodales vitae elit massa. Lorem ipsum dolor sit amet, maecenas eget vestibulum justo imperdiet. Lorem ipsum dolor sit amet, maecenas eget vestibulum justo imperdiet, wisi risus purus augue vulputate voluptate neque, curabitur dolor libero sodales vitae elit massa. Lorem ipsum dolor sit amet, maecenas eget vestibulum justo imperdiet. Lorem ipsum dolor sit amet, maecenas eget vestibulum justo imperdiet, wisi risus purus augue vulputate voluptate neque, curabitur dolor libero sodales vitae elit massa. Lorem ipsum dolor sit amet, maecenas eget vestibulum justo imperdiet.',
  };

  cards: CardResource[] = [
    {
      id: 'asdasdasd',
      imageUrl: '/assets/images/initial.jpg',
      title: 'The best way to make Online Business',
      subtitle: '14 May 2016, 10 comments, David Walker',
    },
    {
      id: 'asdasdasd',
      imageUrl: '/assets/images/initial.jpg',
      title: 'The best way to make Online Business',
      subtitle: '14 May 2016, 10 comments, David Walker',
    },
    {
      id: 'asdasdasd',
      imageUrl: '/assets/images/initial.jpg',
      title: 'The best way to make Online Business',
      subtitle: '14 May 2016, 10 comments, David Walker',
    },
  ];

  constructor() {}
}
