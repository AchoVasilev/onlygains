import { NgModule } from '@angular/core';
import { EditorModule, TINYMCE_SCRIPT_SRC } from '@tinymce/tinymce-angular';
import { TextEditorComponent } from './text-editor/text-editor.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    TextEditorComponent
  ],
  imports: [
    EditorModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    { provide: TINYMCE_SCRIPT_SRC, useValue: 'tinymce/tinymce.min.js' }
  ],
  exports: [
    TextEditorComponent
  ]
})
export class TextEditorModule { }
