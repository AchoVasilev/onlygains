import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ExerciseService } from 'app/core/services/exercise/exercise.service';
import {
  ExerciseDetailsResource,
  ExerciseListResource,
} from 'app/shared/models/exercise';
import { Observable } from 'rxjs';

@Component({
  selector: 'gains-exercise-details',
  templateUrl: './exercise-details.component.html',
  styleUrls: ['./exercise-details.component.scss'],
})
export class ExerciseDetailsComponent implements OnInit {
  // exercise$?: Observable<ExerciseDetailsResource>;
  // exerciseId: string = '';

  constructor(
    private exerciseService: ExerciseService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // this.exerciseId = this.route.snapshot.params['id'];
    // this.exercise$ = this.exerciseService.getById(this.exerciseId);
  }

  getExercise(): ExerciseDetailsResource {
    return {
      id: '1234',
      parentId: null,
      name: 'Shoulder press',
      gifUrl:
        'https://res.cloudinary.com/dpo3vbxnl/image/upload/v1694870493/onlygains/exercises/no-bg_ff5h1t.gif',
      translatedName: 'Раменна преса',
      description: 'description',
      muscleGroups: [
        { name: 'Chest', translatedName: 'Гърди', id: 'Chest' },
        { name: 'Deltoids', translatedName: 'Рамене', id: 'Deltoids' },
        { name: 'Abs', translatedName: 'Корем', id: 'Abs' },
        { name: 'Obliques', translatedName: 'Корем', id: 'Obliques' },
      ],
      mainMuscleGroupIds: ['Deltoids'],
      syngergisticMuscleGroupIds: ['Chest', 'Abs', 'Obliques'],
      variations: this.getExercises(),
      equipment: [{ id: '1234', name: 'dumbbells' }]
    };
  }

  getMainMuscleGroups() {
    return this.getExercise().muscleGroups.filter(gr => this.getExercise().mainMuscleGroupIds.includes(gr.id));
  }

  getSynergists() {
    return this.getExercise().muscleGroups.filter(gr => this.getExercise().syngergisticMuscleGroupIds.includes(gr.id));
  }

  getExercises() {
    const exercises: ExerciseListResource[] = [
      {
        id: '12345',
        name: 'random name',
        imageUrl:
          'https://res.cloudinary.com/dpo3vbxnl/image/upload/v1695099641/onlygains/exercises/reverse-fly_ukqlb1.png',
        equipment: [{ id: '1234', name: 'dumbbells' }],
      },
      {
        id: '12345',
        name: 'random name',
        imageUrl:
          'https://res.cloudinary.com/dpo3vbxnl/image/upload/v1695099641/onlygains/exercises/reverse-fly_ukqlb1.png',
        equipment: [{ id: '1234', name: 'dumbbells' }],
      },
      {
        id: '12345',
        name: 'random name',
        imageUrl:
          'https://res.cloudinary.com/dpo3vbxnl/image/upload/v1695099641/onlygains/exercises/reverse-fly_ukqlb1.png',
        equipment: [{ id: '1234', name: 'dumbbells' }],
      },
      {
        id: '12345',
        name: 'random name',
        imageUrl:
          'https://res.cloudinary.com/dpo3vbxnl/image/upload/v1695099641/onlygains/exercises/reverse-fly_ukqlb1.png',
        equipment: [{ id: '1234', name: 'dumbbells' }],
      },
      {
        id: '12345',
        name: 'random name',
        imageUrl:
          'https://res.cloudinary.com/dpo3vbxnl/image/upload/v1695099641/onlygains/exercises/reverse-fly_ukqlb1.png',
        equipment: [{ id: '1234', name: 'dumbbells' }],
      },
      {
        id: '12345',
        name: 'random name',
        imageUrl:
          'https://res.cloudinary.com/dpo3vbxnl/image/upload/v1695099641/onlygains/exercises/reverse-fly_ukqlb1.png',
        equipment: [
          { id: '1234', name: 'dumbbells' },
          { id: '12345', name: 'barbell' },
          { id: '123456', name: 'bench' },
        ],
      },
    ];

    return exercises;
  }
}
