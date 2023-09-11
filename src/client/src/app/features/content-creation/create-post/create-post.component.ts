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
import { environment } from '../../../../../environments/environment';
import { PostService } from 'app/core/services/post/post.service';

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
    file_picker_callback: (callback, value, meta) =>
      this.onFilePick(callback, value, meta),
  };

  private categoryAnchor?: HTMLElement;

  form = this.fb.group({
    title: this.fb.control<string>('', [Validators.required]),
    text: this.fb.control<string>('', [Validators.required]),
    previewText: this.fb.control<string>('', [Validators.required]),
    categoryId: this.fb.control<string>('', [Validators.required]),
    tags: this.fb.control<string[]>([]),
    imageUrls: this.fb.control<string[]>([]),
  });

  constructor(
    @Inject(DOCUMENT) private document: Document,
    private fb: FormBuilder,
    private categoryService: CategoryService,
    private tagService: TagService,
    private imageService: ImageService,
    private postService: PostService
  ) {
    this.strTemplate = this.source = threeImageTemplate;
  }

  ngOnInit(): void {
    this.categories$ = this.categoryService.getCategories();
    this.tags$ = this.tagService.getTags();
  }

  onEditorInit(event: any) {
    this.editor = event.editor;
    this.categoryAnchor = this.editor!.dom.select('a.post-tag')[0];

    this.editor?.dom.select('img').forEach((img) => {
      this.imageUrls.push(img.getAttribute('src')!);
    });

    this.form.controls.imageUrls.patchValue(this.imageUrls);
    const title = this.editor?.dom.select('h2.title')[0].textContent;
    this.form.controls.title.patchValue(title!);

    const date = this.editor?.dom.select('#date')[0];
    date!.textContent = new Date().toLocaleString('bg-BG');
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
    this.imageUrls = [];
    const upload$ = this.imageService.upload(blobInfo.blob(), 'posts');
    const result = new Promise<string>((resolve, reject) => {
      upload$.subscribe((image) => {
        this.imageUrls.push(image.url);
        resolve(image.url);
      });
    });

    this.form.controls.imageUrls.patchValue(this.imageUrls);
    return result;
  }

  categorySelect(ev: MatSelectChange) {
    this.selectedCategory = ev.value;

    this.editor!.dom.setAttrib(
      this.categoryAnchor!,
      'href',
      this.buildCategoryUrl()
    );
    this.categoryAnchor!.textContent = this.selectedCategory?.name!;

    this.form.controls.categoryId.setValue(this.selectedCategory?.id!);
  }

  onSubmit() {
    const pElement = this.editor?.dom.select('p.post-text')[0];
    this.form.controls.previewText.patchValue(pElement!.textContent);
    const {title, text, imageUrls, categoryId, tags, previewText} = this.form.value;
    const data = {
      title,
      text,
      tags,
      categoryId,
      imageUrls,
      previewText
    };

    //@ts-ignore
    this.postService.createPost(data).subscribe();
  }

  private buildCategoryUrl(): string {
    return `${environment.clientUrl}/posts/${this.selectedCategory?.translatedName}/${this.selectedCategory?.id}`;
  }
}
