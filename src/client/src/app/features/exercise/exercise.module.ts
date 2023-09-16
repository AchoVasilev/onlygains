import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ExerciseRoutingModule } from './exercise-routing.module';
import { ExerciseDetailsComponent } from './exercise-details/exercise-details.component';
import { SharedModule } from 'app/shared/shared-module/shared.module';


@NgModule({
  declarations: [
    ExerciseDetailsComponent
  ],
  imports: [
    CommonModule,
    ExerciseRoutingModule,
    SharedModule
  ]
})
export class ExerciseModule { }
