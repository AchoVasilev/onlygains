import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Editor } from 'tinymce';
import { threeImageTemplate } from 'app/shared/models/text-editor/templates';
import { FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { CategoryDTO, CategoryViewResource } from 'app/shared/models/category';
import { environment } from '../../../../environments/environment';
import { threeImageTemplateStyling } from 'app/shared/models/text-editor/template-stylings';
import { RaisedButtonComponent } from '../../../shared/components/buttons/raised-button/raised-button.component';
import { SelectComponent } from '../../../shared/components/select/select.component';
import { TextEditorComponent } from '../../../shared/components/text-editor/text-editor.component';
import { TagViewResource } from 'app/shared/models/tag';
import { CreatePostResource } from 'app/shared/models/post';

@Component({
  selector: 'active-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss'],
  standalone: true,
  imports: [
    ReactiveFormsModule,
    TextEditorComponent,
    SelectComponent,
    RaisedButtonComponent,
  ]
})
export class CreatePostComponent {
  template: string = threeImageTemplate;
  styling: string = threeImageTemplateStyling;
  editor?: Editor;

  @Input({required: true})
  categories: CategoryViewResource[] | null = [];

  @Input({required: true})
  tags: TagViewResource[] | null = [];

  @Output()
  submit = new EventEmitter<CreatePostResource>();

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
    private fb: FormBuilder
  ) {}

  onEditorInit(ev: Editor) {
    this.editor = ev;
    this.categoryAnchor = this.editor!.dom.select('a.post-tag')[0];

    const imageUrls: string[] = [];
    this.editor?.dom.select('img').forEach((img) => {
      imageUrls.push(img.getAttribute('src')!);
    });

    const date = this.editor?.dom.select('#date')[0];
    if (date) {
      date.textContent = new Date().toLocaleString('bg-BG', {
        month: '2-digit',
        day: '2-digit',
        year: 'numeric',
      });
    }

    this.imageUrls = [...this.imageUrls, ...imageUrls];
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
    this.form.controls.categoryId.setValue(this.selectedCategory?.id!);
  }

  onSubmit() {
    const titleContent = this.editor?.dom.select('h1')[0].textContent;
    this.form.controls.title.patchValue(titleContent!);

    const pElement = this.editor?.dom.select('p.post-text')[0];
    this.form.controls.previewText.patchValue(pElement!.textContent);
    const { title, text, imageUrls, categoryId, tags, previewText } =
      this.form.value;
    const data = {
      title: titleContent,
      text,
      tags,
      categoryId,
      imageUrls,
      previewText,
    };

    this.submit.emit(data);
    this.form.reset();
  }

  private buildCategoryUrl(): string {
    return `${environment.clientUrl}/posts/${this.selectedCategory?.translatedName}/${this.selectedCategory?.id}`;
  }
}
