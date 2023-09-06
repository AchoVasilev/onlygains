import { PostConfig, defaultPostConfig } from 'app/shared/text-editor/config';
import { Component, OnInit } from '@angular/core';
import { threeImageTemplateStyling } from 'app/shared/text-editor/template-stylings';
import { Editor } from 'tinymce';
import { threeImageTemplate } from 'app/shared/text-editor/templates';
import { FormBuilder, Validators } from '@angular/forms';
import { CategoryService } from 'app/core/services/category/category.service';
import { TagService } from 'app/core/services/tag/tag.service';
import { CategoryDTO, CategoryViewResource } from 'app/shared/shared-module/models/category';
import { Observable } from 'rxjs';
import { TagViewResource } from 'app/shared/shared-module/models/tag';
import {C, COMMA, ENTER} from '@angular/cdk/keycodes';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { MatSelectChange } from '@angular/material/select';

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

  separatorKeysCodes: number[] = [ENTER, COMMA];
  categories$?: Observable<CategoryViewResource[]>;
  tags$?: Observable<TagViewResource[]>;
  tags: TagViewResource[] = [];
  selectedCategory?: CategoryDTO;

  form = this.fb.group({
    title: this.fb.control<string>("", [Validators.required]),
    body: this.fb.control<string>("", [Validators.required]),
    category: this.fb.control<CategoryDTO>({}, [Validators.required]),
    tags: this.fb.control<string[]>([])
  })

  constructor(private fb: FormBuilder, private categoryService: CategoryService, private tagService: TagService) {
    this.config.content_style = threeImageTemplateStyling;
    this.config.images_upload_handler = this.onUpload;
    this.strTemplate = this.source = threeImageTemplate;
  }

  ngOnInit(): void {
    this.categories$ = this.categoryService.getCategories();
    this.tags$ = this.tagService.getTags();
  }

  onEditorInit(event: any) {
    this.editor = event.editor;
    
  }
  
  onUpload(blobInfo: any) {
    console.log(blobInfo.blob());
  }

  categorySelect(ev: MatSelectChange) {
    this.selectedCategory = ev.value;
  }
  
  onSubmit() {
    const title = this.editor?.dom.select('h2')[0].textContent;
    this.form.controls.title.setValue(title!);
    
    console.log(this.editor!.dom.select('a.tag')[0].attributes)
    console.log(this.form.value);
    console.log(this.buildCategoryUrl());
  }

  private buildCategoryUrl(): string {
    return `/posts/${this.selectedCategory?.translatedName}/${this.selectedCategory?.id}`;
  }
}
