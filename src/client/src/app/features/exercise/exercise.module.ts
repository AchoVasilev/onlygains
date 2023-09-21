import { NgModule } from '@angular/core';

import { ExerciseRoutingModule } from './exercise-routing.module';
import { ExerciseDetailsComponent } from './exercise-details/exercise-details.component';
import { SharedModule } from 'app/shared/shared-module/shared.module';
import { SimilarExerciseComponent } from 'app/shared/stand-alone/similar-exercise/similar-exercise.component';


@NgModule({
  declarations: [
    ExerciseDetailsComponent
  ],
  imports: [
    ExerciseRoutingModule,
    SharedModule,
    SimilarExerciseComponent
  ]
})
export class ExerciseModule { }
