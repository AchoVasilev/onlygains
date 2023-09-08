import { Component, Inject, OnInit } from '@angular/core';
import { threeImageTemplateStyling } from 'app/shared/text-editor/template-stylings';
import { Editor } from 'tinymce';
import { threeImageTemplate } from 'app/shared/text-editor/templates';
import { FormBuilder, Validators } from '@angular/forms';
import { CategoryService } from 'app/core/services/category/category.service';
import { TagService } from 'app/core/services/tag/tag.service';
import {
  CategoryDTO,
  CategoryViewResource,
} from 'app/shared/shared-module/models/category';
import { Observable } from 'rxjs';
import { TagViewResource } from 'app/shared/shared-module/models/tag';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { MatSelectChange } from '@angular/material/select';
import { ImageService } from 'app/core/services/image/image.service';
import { EditorComponent } from '@tinymce/tinymce-angular';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'gains-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss'],
})
export class CreatePostComponent implements OnInit {
  strTemplate?: string;
  source: string = '';
  editor?: Editor;

  separatorKeysCodes: number[] = [ENTER, COMMA];
  categories$?: Observable<CategoryViewResource[]>;
  tags$?: Observable<TagViewResource[]>;
  tags: TagViewResource[] = [];
  selectedCategory?: CategoryDTO;
  imageUrls: string[] = [];

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
    file_picker_callback: (callback, value, meta) => this.onFilePick(callback, value, meta),
  };

  form = this.fb.group({
    title: this.fb.control<string>('', [Validators.required]),
    body: this.fb.control<string>('', [Validators.required]),
    category: this.fb.control<CategoryDTO>({}, [Validators.required]),
    tags: this.fb.control<string[]>([]),
    imageUrls: this.fb.control<string[]>([]),
  });

  constructor(
    @Inject(DOCUMENT) private document: Document,
    private fb: FormBuilder,
    private categoryService: CategoryService,
    private tagService: TagService,
    private imageService: ImageService
  ) {
    this.strTemplate = this.source = threeImageTemplate;
  }

  ngOnInit(): void {
    this.categories$ = this.categoryService.getCategories();
    this.tags$ = this.tagService.getTags();
  }

  onEditorInit(event: any) {
    this.editor = event.editor;
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
        const id = 'blobid' + (new Date()).getTime();
        const blobCache =  this.editor?.editorUpload.blobCache;
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
    let imageUrl = '';
    const upload$ = this.imageService.upload(blobInfo.blob(), 'posts');
    const result = new Promise<string>((resolve, reject) => {
      upload$.subscribe((url) => {
        imageUrl = url;
        resolve(url);
      });
    });

    this.imageUrls.push(imageUrl);

    return result;
  }

  categorySelect(ev: MatSelectChange) {
    this.selectedCategory = ev.value;
  }

  onSubmit() {
    const title = this.editor?.dom.select('h2')[0].textContent;
    this.form.controls.title.setValue(title!);

    console.log(this.editor!.dom.select('a.tag')[0].attributes);
    console.log(this.form.value);
    console.log(this.buildCategoryUrl());
  }

  private buildCategoryUrl(): string {
    return `/posts/${this.selectedCategory?.translatedName}/${this.selectedCategory?.id}`;
  }
}
