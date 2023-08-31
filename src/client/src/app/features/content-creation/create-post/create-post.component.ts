import { PostConfig, defaultPostConfig } from 'app/shared/text-editor/config';
import { Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { threeImageTemplateStyling } from 'app/shared/text-editor/template-stylings';
import { Editor } from 'tinymce';
import { threeImageTemplate } from 'app/shared/text-editor/templates';

@Component({
  selector: 'gains-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss'],
})
export class CreatePostComponent implements OnInit {
  @ViewChild('preview', {static: true}) preview?: ElementRef;
  config: PostConfig = defaultPostConfig;
  strTemplate?: string;
  source: string = '';
  editor?: Editor;

  constructor(private renderer: Renderer2) {
    this.config.content_style = threeImageTemplateStyling;
    this.config.images_upload_handler = this.onUpload;
    this.strTemplate = this.source = threeImageTemplate;
  }

  ngOnInit(): void {
  }

  onEditorInit(event: any) {
    this.editor = event.editor;
    console.log(this.editor?.dom.select('h2')[0].textContent)

    this.renderer.setProperty(this.preview?.nativeElement, 'innerHTML', this.source);
  }

  onUpload(blobInfo: any) {
    console.log(blobInfo.blob());
  }

  onModelChange() {
    this.renderer.setProperty(this.preview?.nativeElement, 'innerHTML', this.source);
  }
}
