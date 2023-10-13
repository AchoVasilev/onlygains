import { Component, ElementRef, ViewChild } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { EquipmentService } from 'app/core/services/equipment/equipment.service';
import { ExerciseService } from 'app/core/services/exercise/exercise.service';
import { MuscleGroupService } from 'app/core/services/muscle-group/muscle-group.service';
import { CategoryDTO } from 'app/shared/models/category';
import { EquipmentResource } from 'app/shared/models/equipment';
import {
  ExerciseResource,
  MuscleGroupDetailsResource,
} from 'app/shared/models/exercise';
import { exerciseTemplateStyling } from 'app/shared/models/text-editor/template-stylings';
import { exerciseTemplate } from 'app/shared/models/text-editor/templates';
import { Observable } from 'rxjs';
import { Editor } from 'tinymce';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';

@Component({
  selector: 'gains-create-exercise',
  templateUrl: './create-exercise.component.html',
  styleUrls: ['./create-exercise.component.scss'],
})
export class CreateExerciseComponent {
  template: string = exerciseTemplate;
  styling: string = exerciseTemplateStyling;
  editor?: Editor;

  equipment$?: Observable<EquipmentResource[]>;

  selectedCategory?: CategoryDTO;
  muscleGroups: MuscleGroupDetailsResource[] = [];
  variations$?: Observable<ExerciseResource[]>;
  selectedVariations: ExerciseResource[] = [];
  separatorKeysCodes: number[] = [ENTER, COMMA];
  
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

  constructor(
    private fb: FormBuilder,
    private readonly equipmentService: EquipmentService,
    private readonly muscleGroupService: MuscleGroupService,
    private readonly exerciseService: ExerciseService
  ) {}

  ngOnInit(): void {
    this.equipment$ = this.equipmentService.getAll();
    this.getMuscleGroups();
  }

  getMuscleGroups() {
    return this.muscleGroupService
      .getAll()
      .subscribe((gr) => (this.muscleGroups = gr));
  }

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

  onInput(ev: any) {
    const input = ev.data ? ev.data : '';
    this.variations$ = this.exerciseService.getVariations(input);
  }

  onEditorInputChange(ev: string) {
    this.form.controls.description.patchValue(ev);
  }

  remove(variation: ExerciseResource): void {
    const index = this.selectedVariations.indexOf(variation);

    if (index >= 0) {
      this.selectedVariations.splice(index, 1);
    }
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.selectedVariations.push(event.option.value);
    this.variationInput!.nativeElement.value = '';
    this.form.controls.variations.patchValue(this.selectedVariations.map(v => v.id));
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
    };

    this.exerciseService.createExercise(data).subscribe();
  }
}
