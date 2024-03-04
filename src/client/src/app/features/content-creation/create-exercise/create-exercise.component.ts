import {
  Component,
  ElementRef,
  EventEmitter,
  Input,
  Output,
  ViewChild,
} from '@angular/core';
import { Validators, FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { EquipmentResource } from 'app/shared/models/equipment';
import {
  CreateExerciseResource,
  ExerciseResource,
  MuscleGroupDetailsResource,
} from 'app/shared/models/exercise';
import { exerciseTemplateStyling } from 'app/shared/models/text-editor/template-stylings';
import { exerciseTemplate } from 'app/shared/models/text-editor/templates';
import { Editor } from 'tinymce';
import { RaisedButtonComponent } from '../../../shared/components/buttons/raised-button/raised-button.component';
import { AutoCompleteComponent } from '../../../shared/components/auto-complete/auto-complete.component';
import { SelectComponent } from '../../../shared/components/select/select.component';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { TextEditorComponent } from '../../../shared/components/text-editor/text-editor.component';

@Component({
  selector: 'active-create-exercise',
  templateUrl: './create-exercise.component.html',
  styleUrls: ['./create-exercise.component.scss'],
  standalone: true,
  imports: [
    ReactiveFormsModule,
    TextEditorComponent,
    MatFormFieldModule,
    MatInputModule,
    SelectComponent,
    AutoCompleteComponent,
    RaisedButtonComponent,
  ],
})
export class CreateExerciseComponent {
  template: string = exerciseTemplate;
  styling: string = exerciseTemplateStyling;
  editor?: Editor;

  @Input({ required: true })
  equipment: EquipmentResource[] | null = [];

  @Input({ required: true })
  muscleGroups: MuscleGroupDetailsResource[] | null = [];

  @Input({ required: true })
  variations: ExerciseResource[] | null = [];

  @Output()
  filter = new EventEmitter<string>();

  @Output()
  create = new EventEmitter<CreateExerciseResource>();

  form = this.fb.group({
    name: this.fb.control<string>('', [Validators.required]),
    translatedName: this.fb.control<string>('', [Validators.required]),
    description: this.fb.control<string>('', [Validators.required]),
    equipment: this.fb.control<string[]>([], [Validators.required]),
    mainMuscleGroupsIds: this.fb.control<string[]>([]),
    synergisticMuscleGroupsIds: this.fb.control<string[]>([]),
    gifUrl: this.fb.control<string>('', [Validators.required]),
    imageUrl: this.fb.control<string>(
      'https://res.cloudinary.com/dpo3vbxnl/image/upload/v1691941224/onlygains/categories/brutally-hardcore-gyms-you-need-to-train-at-before-you-die-652x400-10-1496399800_ahp7xa.jpg',
      [Validators.required]
    ),
    variations: this.fb.control<string[]>([]),
  });

  @ViewChild('variationInput') variationInput?: ElementRef<HTMLInputElement>;

  constructor(private fb: FormBuilder) {}

  onEditorInit(ev: Editor) {
    this.editor = ev;

    const gifUrl = this.editor?.dom.select('img')[0].getAttribute('src');

    const title = this.editor?.dom.select('h1')[0].textContent;
    this.form.controls.name.patchValue(title!);
    this.form.controls.gifUrl.patchValue(gifUrl);

    const content = this.editor.getContent();
    this.form.controls.description.setValue(content);
  }

  onImageUpload(imageUrl: string) {
    if (imageUrl.endsWith('gif')) {
      this.form.controls.gifUrl.patchValue(imageUrl);
    } else {
      this.form.controls.imageUrl.patchValue(imageUrl);
    }
  }

  onInput(ev: string) {
    this.filter.emit(ev);
  }

  onEditorInputChange(ev: string) {
    this.form.controls.description.patchValue(ev);
  }

  onSubmit() {
    const title = this.editor?.dom.select('h1')[0].textContent;
    this.form.controls.name.patchValue(title!);

    const {
      name,
      translatedName,
      description,
      gifUrl,
      imageUrl,
      equipment,
      mainMuscleGroupsIds,
      synergisticMuscleGroupsIds,
      variations,
    } = this.form.value;
    const data = {
      name,
      translatedName,
      description,
      mainMuscleGroupsIds,
      synergisticMuscleGroupsIds,
      equipment,
      gifUrl,
      imageUrl,
      variations,
    };

    this.create.emit(data);
    this.form.reset();
  }
}
