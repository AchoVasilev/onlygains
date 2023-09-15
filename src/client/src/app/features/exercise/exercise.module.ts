import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ExerciseRoutingModule } from './exercise-routing.module';
import { ExerciseDetailsComponent } from './exercise-details/exercise-details.component';


@NgModule({
  declarations: [
    ExerciseDetailsComponent
  ],
  imports: [
    CommonModule,
    ExerciseRoutingModule
  ]
})
export class ExerciseModule { }
