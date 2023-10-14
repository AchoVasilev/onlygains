import { Component, OnInit } from '@angular/core';
import { Editor } from 'tinymce';
import { threeImageTemplate } from 'app/shared/models/text-editor/templates';
import { FormBuilder, Validators } from '@angular/forms';
import { CategoryService } from 'app/core/services/category/category.service';
import { TagService } from 'app/core/services/tag/tag.service';
import { CategoryDTO, CategoryViewResource } from 'app/shared/models/category';
import { Observable } from 'rxjs';
import { TagViewResource } from 'app/shared/models/tag';
import { environment } from '../../../../environments/environment';
import { PostService } from 'app/core/services/post/post.service';
import { threeImageTemplateStyling } from 'app/shared/models/text-editor/template-stylings';

@Component({
  selector: 'gains-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss'],
})
export class CreatePostComponent implements OnInit {
  template: string = threeImageTemplate;
  styling: string = threeImageTemplateStyling;
  editor?: Editor;

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
    private fb: FormBuilder,
    private categoryService: CategoryService,
    private tagService: TagService,
    private postService: PostService
  ) {}

  ngOnInit(): void {
    this.categories$ = this.categoryService.getCategories();
    this.tags$ = this.tagService.getTags();
  }

  onEditorInit(ev: Editor) {
    this.editor = ev;
    this.categoryAnchor = this.editor!.dom.select('a.post-tag')[0];

    const imageUrls: string[] = [];
    this.editor?.dom.select('img').forEach((img) => {
      imageUrls.push(img.getAttribute('src')!);
    });

    const title = this.editor?.dom.select('h1')[0].textContent;

    const date = this.editor?.dom.select('#date')[0];
    if (date) {
      date.textContent = new Date().toLocaleString('bg-BG', {
        month: '2-digit',day: '2-digit',year: 'numeric'});
    }

    this.imageUrls = [...this.imageUrls, ...imageUrls];
    this.form.controls.title.patchValue(title!);
    this.form.controls.imageUrls.patchValue(this.imageUrls);

    const content = this.editor?.getContent();
    this.form.controls.text.setValue(content);
  }

  onImageUpload(imageUrl: string) {
    this.imageUrls.push(imageUrl);
    this.form.controls.imageUrls.patchValue(this.imageUrls);
  }

  onEditorInputChange(ev: string) {
    const pElement = this.editor?.dom.select('p.post-text')[0].textContent;
    this.form.controls.previewText.patchValue(pElement!);
    this.form.controls.text.patchValue(ev);
  }

  categorySelect(ev: any) {
    this.selectedCategory = ev;
    this.editor!.dom.setAttrib(
      this.categoryAnchor!,
      'href',
      this.buildCategoryUrl()
    );
    this.categoryAnchor!.textContent = this.selectedCategory?.name!;
  }

  onSubmit() {
    this.form.controls.categoryId.setValue(this.selectedCategory?.id!);
    const pElement = this.editor?.dom.select('p.post-text')[0];
    this.form.controls.previewText.patchValue(pElement!.textContent);
    const { title, text, imageUrls, categoryId, tags, previewText } =
      this.form.value;
    const data = {
      title,
      text,
      tags,
      categoryId,
      imageUrls,
      previewText,
    };

    //@ts-ignore
    this.postService.createPost(data).subscribe();
    this.form.reset();
  }

  private buildCategoryUrl(): string {
    return `${environment.clientUrl}/posts/${this.selectedCategory?.translatedName}/${this.selectedCategory?.id}`;
  }
}
