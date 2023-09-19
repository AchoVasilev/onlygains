import { NgModule } from '@angular/core';

import { ContentCreationRoutingModule } from './content-creation-routing.module';
import { CreatePostComponent } from './create-post/create-post.component';
import { TextEditorModule } from 'app/shared/text-editor/text-editor.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from 'app/shared/shared-module/shared.module';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CreateExerciseComponent } from './create-exercise/create-exercise.component';

@NgModule({
  declarations: [CreatePostComponent, CreateExerciseComponent],
  imports: [
    ContentCreationRoutingModule,
    TextEditorModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatFormFieldModule,
  ],
})
export class ContentCreationModule {}
