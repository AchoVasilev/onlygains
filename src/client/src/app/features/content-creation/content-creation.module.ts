import { NgModule } from '@angular/core';

import { ContentCreationRoutingModule } from './content-creation-routing.module';
import { CreatePostComponent } from './create-post/create-post.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CreateExerciseComponent } from './create-exercise/create-exercise.component';
import { TextEditorComponent } from 'app/shared/components/text-editor/text-editor.component';

@NgModule({
  declarations: [CreatePostComponent, CreateExerciseComponent],
  imports: [
    ContentCreationRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatFormFieldModule,
    TextEditorComponent
  ],
})
export class ContentCreationModule {}
