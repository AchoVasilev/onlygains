import { Component, OnInit } from '@angular/core';
import { ExerciseService } from 'app/core/services/exercise/exercise.service';

@Component({
  selector: 'gains-exercise-details',
  templateUrl: './exercise-details.component.html',
  styleUrls: ['./exercise-details.component.scss']
})
export class ExerciseDetailsComponent implements OnInit{

  constructor(private exerciseService: ExerciseService) {}

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

}
