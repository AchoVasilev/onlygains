import { NgModule } from '@angular/core';

import { ExerciseRoutingModule } from './exercise-routing.module';
import { ExerciseDetailsComponent } from './exercise-details/exercise-details.component';
import { SharedModule } from 'app/shared/shared-module/shared.module';


@NgModule({
  declarations: [
    ExerciseDetailsComponent
  ],
  imports: [
    ExerciseRoutingModule,
    SharedModule
  ]
})
export class ExerciseModule { }
