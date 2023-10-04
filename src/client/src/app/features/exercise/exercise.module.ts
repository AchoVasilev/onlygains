import { NgModule } from '@angular/core';

import { ExerciseRoutingModule } from './exercise-routing.module';
import { ExerciseDetailsComponent } from './exercise-details/exercise-details.component';
import { SimilarExerciseComponent } from 'app/shared/components/similar-exercise/similar-exercise.component';
import { MuscleGroupComponent } from 'app/shared/components/muscle-group/muscle-group.component';
import { SideBarComponent } from 'app/shared/components/side-bar/side-bar.component';
import { NgForTrackByIdDirective } from 'app/shared/directives/ng-for-track-by-id.directive';
import { CommonModule } from '@angular/common';


@NgModule({
  declarations: [
    ExerciseDetailsComponent
  ],
  imports: [
    CommonModule,
    ExerciseRoutingModule,
    SimilarExerciseComponent,
    MuscleGroupComponent,
    SideBarComponent,
    NgForTrackByIdDirective
  ]
})
export class ExerciseModule { }
