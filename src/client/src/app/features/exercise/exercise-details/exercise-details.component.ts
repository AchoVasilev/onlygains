import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ExerciseService } from 'app/core/services/exercise/exercise.service';
import {
  ExerciseDetailsResource,
  ExerciseListResource,
} from 'app/shared/shared-module/models/exercise';
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
        equipment: [{ id: '1234', name: 'dumbbells' }, {id: '12345', name: 'barbell'}, {id: '123456', name: 'bench'}],
      },
    ];

    return exercises;
  }
}
