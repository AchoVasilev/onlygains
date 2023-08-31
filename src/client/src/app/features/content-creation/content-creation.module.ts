import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ContentCreationRoutingModule } from './content-creation-routing.module';
import { CreatePostComponent } from './create-post/create-post.component';
import { TextEditorModule } from 'app/shared/text-editor/text-editor.module';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [CreatePostComponent],
  imports: [
    CommonModule,
    ContentCreationRoutingModule,
    TextEditorModule,
    FormsModule
  ]
})
export class ContentCreationModule { }
