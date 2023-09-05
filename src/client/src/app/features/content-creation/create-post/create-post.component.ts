import { PostConfig, defaultPostConfig } from 'app/shared/text-editor/config';
import { Component, OnInit } from '@angular/core';
import { threeImageTemplateStyling } from 'app/shared/text-editor/template-stylings';
import { Editor } from 'tinymce';
import { threeImageTemplate } from 'app/shared/text-editor/templates';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'gains-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss'],
})
export class CreatePostComponent implements OnInit {
  config: PostConfig = defaultPostConfig;
  strTemplate?: string;
  source: string = '';
  editor?: Editor;

  form = this.fb.group({
    title: this.fb.control("", [Validators.required]),
    body: this.fb.control("", [Validators.required])
  })

  constructor(private fb: FormBuilder) {
    this.config.content_style = threeImageTemplateStyling;
    this.config.images_upload_handler = this.onUpload;
    this.strTemplate = this.source = threeImageTemplate;
  }

  ngOnInit(): void {
  }

  onEditorInit(event: any) {
    this.editor = event.editor;
    
  }
  
  onUpload(blobInfo: any) {
    console.log(blobInfo.blob());
  }
  
  onSubmit() {
    const title = this.editor?.dom.select('h2')[0].textContent
    const text = this.editor?.dom.select('p').map(p => p?.textContent).reduce((a: any, b:any) => a + b);
    console.log(this.editor?.getContent({format: 'text'}))
  }
}
