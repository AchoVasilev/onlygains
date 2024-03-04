import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ExerciseService } from 'app/core/services/exercise/exercise.service';
import { ExerciseDetailsResource } from 'app/shared/models/exercise';
import { SideBarComponent } from '../../../shared/components/side-bar/side-bar.component';
import { SimilarExerciseComponent } from '../../../shared/components/similar-exercise/similar-exercise.component';
import { MuscleGroupComponent } from '../../../shared/components/muscle-group/muscle-group.component';
import { LowerCasePipe } from '@angular/common';

@Component({
  selector: 'active-exercise-details',
  templateUrl: './exercise-details.component.html',
  styleUrls: ['./exercise-details.component.scss'],
  standalone: true,
  imports: [
    MuscleGroupComponent,
    SimilarExerciseComponent,
    SideBarComponent,
    LowerCasePipe,
  ],
})
export class ExerciseDetailsComponent implements OnInit {
  exercise?: ExerciseDetailsResource;
  exerciseId: string = '';

  constructor(
    private exerciseService: ExerciseService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.exerciseId = this.route.snapshot.params['id'];
    this.getExercise();
  }

  getExercise(): void {
    this.exerciseService
      .getById(this.exerciseId)
      .subscribe(exercise => (this.exercise = exercise));
  }

  getMainMuscleGroups() {
    return this.exercise?.muscleGroups?.filter(gr =>
      this.exercise?.mainMuscleGroupsIds?.includes(gr.id)
    );
  }

  getSynergists() {
    return this.exercise?.muscleGroups?.filter(gr =>
      this.exercise?.synergisticMuscleGroupsIds?.includes(gr.id)
    );
  }

  getVariations() {
    // if (this.exercise?.variations) {
    // }
  }
}
