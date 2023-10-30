import { FormGroup, Validators, FormControl } from '@angular/forms';
import {
  exerciseTemplateStyling,
  threeImageTemplateStyling,
} from 'app/shared/models/text-editor/template-stylings';
import {
  exerciseTemplate,
  threeImageTemplate,
} from 'app/shared/models/text-editor/templates';
import { Editor } from 'tinymce';

export const ContentResolver: ContentTypeResolver = {
  exercise: {
    template: exerciseTemplate,
    styling: exerciseTemplateStyling,
    form: new FormGroup({
      name: new FormControl<string>('', [Validators.required]),
      translatedName: new FormControl<string>('', [Validators.required]),
      description: new FormControl<string>('', [Validators.required]),
      equipment: new FormControl<string[]>([], [Validators.required]),
      mainMuscleGroupsIds: new FormControl<string[]>([]),
      synergisticMuscleGroupsIds: new FormControl<string[]>([]),
      gifUrl: new FormControl<string>('', [Validators.required]),
      imageUrl: new FormControl<string>(
        'https://res.cloudinary.com/dpo3vbxnl/image/upload/v1691941224/onlygains/categories/brutally-hardcore-gyms-you-need-to-train-at-before-you-die-652x400-10-1496399800_ahp7xa.jpg',
        [Validators.required]
      ),
      variations: new FormControl<string[]>([]),
    }),
    patchForm: (editor) => {
      const gifUrl = editor?.dom.select('img')[0].getAttribute('src');

      const title = editor?.dom.select('h1')[0].textContent;
      ContentResolver['exercise'].form.controls['name'].patchValue(title!);
      ContentResolver['exercise'].form.controls['gifUrl'].patchValue(gifUrl);

      const content = editor?.getContent();
      ContentResolver['exercise'].form.controls['description'].setValue(
        content
      );
    },
  },
  post: {
    template: threeImageTemplate,
    styling: threeImageTemplateStyling,
    form: new FormGroup({
      title: new FormControl<string>('', [Validators.required]),
      text: new FormControl<string>('', [Validators.required]),
      previewText: new FormControl<string>('', [Validators.required]),
      categoryId: new FormControl<string>('', [Validators.required]),
      tags: new FormControl<string[]>([]),
      imageUrls: new FormControl<string[]>([]),
    }),
    patchForm: (editor) => {
      const imageUrls: string[] = [];
      editor?.dom.select('img').forEach((img) => {
        imageUrls.push(img.getAttribute('src')!);
      });

      const title = editor?.dom.select('h1')[0].textContent;

      const date = editor?.dom.select('#date')[0];
      if (date) {
        date.textContent = new Date().toLocaleString('bg-BG', {
          month: '2-digit',
          day: '2-digit',
          year: 'numeric',
        });
      }

      ContentResolver['post'].form.controls['title'].patchValue(title!);
      ContentResolver['post'].form.controls['imageUrls'].patchValue(imageUrls);

      const content = editor?.getContent();
      ContentResolver['post'].form.controls['text'].setValue(content);
    },
  },
};

export interface ContentTypeResolver {
  [key: string]: Content;
}

export interface Content {
  template: string;
  styling: string;
  form: FormGroup;
  patchForm: (editor: Editor) => void;
}
