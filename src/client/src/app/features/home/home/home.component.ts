import { PostService } from './../../../core/services/post/post.service';
import { Component } from '@angular/core';

@Component({
  selector: 'gains-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  animations: [
    
  ]
})
export class HomeComponent {

  constructor(private postService: PostService) {}

  
}
