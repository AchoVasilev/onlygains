import { Component, EventEmitter, Inject, Output } from '@angular/core';
import { EditorComponent } from '@tinymce/tinymce-angular';
import { threeImageTemplateStyling } from '../template-stylings';
import { Editor } from 'tinymce';
import { threeImageTemplate } from '../templates';
import { DOCUMENT } from '@angular/common';
import { EditorOnInit } from '../editor-model';
import { ImageService } from 'app/core/services/image/image.service';

@Component({
  selector: 'gains-text-editor',
  templateUrl: './text-editor.component.html',
  styleUrls: ['./text-editor.component.scss']
})
export class TextEditorComponent {
  strTemplate?: string;
  source: string = '';
  editor?: Editor;

  @Output()
  editorInit: EventEmitter<EditorOnInit> = new EventEmitter();

  @Output()
  imageUpload: EventEmitter<string> = new EventEmitter();

  @Output()
  inputChanged: EventEmitter<string> = new EventEmitter();
  
  defaultPostConfig: EditorComponent['init'] = {
    base_url: '/tinymce',
    suffix: '.min',
    selector: '#textarea',
    height: 'calc(100vh - 88px)',
    menubar: true,
    language: 'bg_BG',
    language_url: 'assets/langs/bg_BG.js',
    toolbar:
      'undo redo | formatselect | bold italic backcolor | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | removeformat | help | preview',
    promotion: false,
    branding: false,
    plugins: ['anchor', 'link', 'lists', 'image', 'preview'],
    content_style: threeImageTemplateStyling,
    file_picker_types: 'image',
    image_title: true,
    automatic_uploads: true,
    images_upload_handler: (blobInfo) => this.onUpload(blobInfo),
    file_picker_callback: (callback, value, meta) =>
      this.onFilePick(callback, value, meta),
  };

  private categoryAnchor?: HTMLElement;

  constructor(@Inject(DOCUMENT) private document: Document, private imageService: ImageService) {
    this.strTemplate = this.source = threeImageTemplate;
  }

  onEditorInit(event: any) {
    this.editor = event.editor;
    this.categoryAnchor = this.editor!.dom.select('a.post-tag')[0];

    const imageUrls: string[] = [];
    this.editor?.dom.select('img').forEach((img) => {
      imageUrls.push(img.getAttribute('src')!);
    });

    const title = this.editor?.dom.select('h2.title')[0].textContent;

    const date = this.editor?.dom.select('#date')[0];
    date!.textContent = new Date().toLocaleString('bg-BG');

    this.editorInit.emit({imageUrls, title})
  }

  onFilePick(callback: any, value: any, meta: any) {
    const input = this.document.createElement('input');
    input.setAttribute('type', 'file');
    input.setAttribute('accept', 'image/*');

    input.addEventListener('change', () => {
      const file = input.files![0];
      const reader = new FileReader();

      reader.addEventListener('load', () => {
        /*
          Note: Now we need to register the blob in TinyMCEs image blob
          registry. In the next release this part hopefully won't be
          necessary, as we are looking to handle it internally.
        */
        const id = 'blobid' + new Date().getTime();
        const blobCache = this.editor?.editorUpload.blobCache;
        const base64 = (reader.result as string).split(',')[1];
        const blobInfo = blobCache?.create(id, file, base64!);
        blobCache?.add(blobInfo!);

        /* call the callback and populate the Title field with the file name */
        callback(blobInfo?.blobUri(), { title: file.name });
      });

      reader.readAsDataURL(file);
    });

    input.click();
  }

  onUpload(blobInfo: any) {
    const upload$ = this.imageService.upload(blobInfo.blob(), 'posts');
    const result = new Promise<string>((resolve, reject) => {
      upload$.subscribe((image) => {
        this.imageUpload.emit(image.url);
        resolve(image.url);
      });
    });

    return result;
  }

  onEditorChange() {
    this.inputChanged.emit(this.source);
  }
}
