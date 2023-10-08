import { Component } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { EquipmentService } from 'app/core/services/equipment/equipment.service';
import { ExerciseService } from 'app/core/services/exercise/exercise.service';
import { MuscleGroupService } from 'app/core/services/muscle-group/muscle-group.service';
import { CategoryDTO } from 'app/shared/models/category';
import { EquipmentResource } from 'app/shared/models/equipment';
import { MuscleGroupDetailsResource } from 'app/shared/models/exercise';
import { exerciseTemplateStyling } from 'app/shared/models/text-editor/template-stylings';
import { exerciseTemplate } from 'app/shared/models/text-editor/templates';
import { Observable } from 'rxjs';
import { Editor } from 'tinymce';

@Component({
  selector: 'gains-create-exercise',
  templateUrl: './create-exercise.component.html',
  styleUrls: ['./create-exercise.component.scss']
})
export class CreateExerciseComponent {
  template: string = exerciseTemplate;
  styling: string = exerciseTemplateStyling;
  editor?: Editor;

  equipment$?: Observable<EquipmentResource[]>;

  selectedCategory?: CategoryDTO;
  muscleGroups: MuscleGroupDetailsResource[] = [];

  form = this.fb.group({
    name: this.fb.control<string>('', [Validators.required]),
    translatedName: this.fb.control<string>('', [Validators.required]),
    description: this.fb.control<string>('', [Validators.required]),
    equipment: this.fb.control<string[]>([], [Validators.required]),
    mainMuscleGroupsIds: this.fb.control<string[]>([]),
    synergisticMuscleGroupsIds: this.fb.control<string[]>([]),
    gifUrl: this.fb.control<string>('', [Validators.required]),
    imageUrl: this.fb.control<string>('', [Validators.required])
  });

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
    return this.muscleGroupService.getAll().subscribe(gr => this.muscleGroups = gr);
  }

  onEditorInit(ev: Editor) {
    this.editor = ev;

    const gifUrl = this.editor?.dom.select('img')[0].getAttribute('src');

    const title = this.editor?.dom.select('h1')[0].textContent;

    this.form.controls.name.patchValue(title!);
    this.form.controls.gifUrl.patchValue(gifUrl);
  }

  onImageUpload(imageUrl: string) {
    if (imageUrl.endsWith('gif')) {
      this.form.controls.gifUrl.patchValue(imageUrl);
    } else {
      this.form.controls.imageUrl.patchValue(imageUrl);
    }
  }

  onEditorInputChange(ev: string) {
    this.form.controls.description.patchValue(ev);
  }

  onSubmit() {
    const { name, translatedName, description, gifUrl, imageUrl, equipment, mainMuscleGroupsIds, synergisticMuscleGroupsIds } =
      this.form.value;
    const data = {
      name,
      translatedName,
      description,
      mainMuscleGroupsIds,
      synergisticMuscleGroupsIds,
      equipment,
      gifUrl,
      imageUrl
    };

    this.exerciseService.createExercise(data).subscribe();
  }
}
