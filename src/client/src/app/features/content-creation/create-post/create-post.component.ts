import { PostConfig, defaultPostConfig } from '../../../shared/text-editor/config';
import { Component, OnInit } from '@angular/core';
import { threeImageTemplateStyling } from 'app/shared/text-editor/template-stylings';
import { threeImageTemplate } from 'app/shared/text-editor/templates';

@Component({
  selector: 'gains-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss'],
})
export class CreatePostComponent implements OnInit {

  config: PostConfig = defaultPostConfig;
  strTemplate?: string;
  source: string = '';

  constructor() {
    this.config.content_style = threeImageTemplateStyling;
    this.config.images_upload_handler = this.onUpload;
    this.strTemplate = this.source = threeImageTemplate;
  }

  ngOnInit(): void {
  }

  onEditorInit(event: any) {
    console.log(event.editor.getContent())
  }

  onUpload(blobInfo: any) {
    console.log(blobInfo.blob());
  }
}
