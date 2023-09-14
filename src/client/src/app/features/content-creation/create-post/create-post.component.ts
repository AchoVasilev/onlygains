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
import { EditorInputChange, EditorOnInit } from 'app/shared/text-editor/editor-model';

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

  onEditorInit(ev: EditorOnInit) {
    this.imageUrls = [...this.imageUrls, ...ev.imageUrls];
    this.form.controls.title.patchValue(ev.title!);
    this.form.controls.imageUrls.patchValue(this.imageUrls);
  }

  onImageUpload(imageUrl: string) {
    this.imageUrls.push(imageUrl);
    this.form.controls.imageUrls.patchValue(this.imageUrls);
  }

  onEditorInputChange(ev: EditorInputChange) {
    this.form.controls.previewText.patchValue(ev.previewText!);
    this.form.controls.text.patchValue(ev.body);
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
