import {
  Component,
  EventEmitter,
  Inject,
  Input,
  OnInit,
  Output,
} from '@angular/core';
import { DOCUMENT, NgIf } from '@angular/common';
import {
  EditorComponent,
  EditorModule,
  TINYMCE_SCRIPT_SRC,
} from '@tinymce/tinymce-angular';
import { ImageService } from 'app/core/services/image/image.service';
import { Editor } from 'tinymce';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'active-text-editor',
  standalone: true,
  imports: [EditorModule, FormsModule, ReactiveFormsModule, NgIf],
  providers: [
    { provide: TINYMCE_SCRIPT_SRC, useValue: 'tinymce/tinymce.min.js' },
  ],
  templateUrl: './text-editor.component.html',
  styleUrls: ['./text-editor.component.scss'],
})
export class TextEditorComponent implements OnInit {
  source: string = '';
  editor?: Editor;

  @Output()
  editorInit: EventEmitter<Editor> = new EventEmitter();

  @Output()
  imageUpload: EventEmitter<string> = new EventEmitter();

  @Output()
  inputChanged: EventEmitter<string> = new EventEmitter();

  @Input()
  styling?: string;

  @Input()
  folderUpload: string = '';

  @Input()
  template?: string;

  editorConfig: EditorComponent['init'] = {
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
    file_picker_types: 'image',
    image_title: true,
    automatic_uploads: true,
  };

  constructor(
    @Inject(DOCUMENT) private document: Document,
    private imageService: ImageService
  ) {}

  ngOnInit(): void {
    if (this.styling) {
      this.editorConfig!.images_upload_handler = (blobInfo) =>
        this.onUpload(blobInfo);
      this.editorConfig!.file_picker_callback = (callback, value, meta) =>
        this.onFilePick(callback, value, meta);
      this.editorConfig!.content_style = this.styling;
    }

    if (this.template) {
      this.source = this.template;
    }
  }

  onEditorInit(event: any) {
    this.editor = event.editor;
    this.editorInit.emit(this.editor);
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
    const upload$ = this.imageService.upload(blobInfo.blob(), this.folderUpload);
    const result = new Promise<string>((resolve) => {
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
