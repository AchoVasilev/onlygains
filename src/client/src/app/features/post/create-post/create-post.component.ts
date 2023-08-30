import { PostConfig, defaultPostConfig } from './../../../shared/text-editor/config';
import { Component } from '@angular/core';

@Component({
  selector: 'gains-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent {

  config?: PostConfig = defaultPostConfig;


}
