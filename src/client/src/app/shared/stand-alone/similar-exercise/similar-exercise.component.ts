import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ExerciseListResource } from 'app/shared/shared-module/models/exercise';

@Component({
  selector: 'gains-similar-exercise',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './similar-exercise.component.html',
  styleUrls: ['./similar-exercise.component.scss']
})
export class SimilarExerciseComponent {

  @Input()
  exercise?: ExerciseListResource | null;

  getEquipment() {
    return this.exercise?.equipment.join(', ');
  }
}
