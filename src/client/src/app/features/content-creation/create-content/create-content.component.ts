import { ExerciseService } from 'app/core/services/exercise/exercise.service';
import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Content, ContentResolver } from '../content';
import { Editor } from 'tinymce';
import { FormGroup } from '@angular/forms';
import { PostService } from 'app/core/services/post/post.service';

@Component({
  selector: 'gains-create-content',
  templateUrl: './create-content.component.html',
  styleUrls: ['./create-content.component.scss'],
})
export class CreateContentComponent implements OnInit, AfterViewInit {
  private editor?: Editor;

  contentType?: string;
  title?: string;
  content?: Content;

  form!: FormGroup;

  constructor(private route: ActivatedRoute, private postService: PostService, private exerciseService: ExerciseService) {}

  ngOnInit(): void {
    this.contentType = this.route.snapshot.params['type'];
    this.content = this.getContent(this.contentType!);
    this.resolveTitle(this.contentType!);
    this.buildForm();
  }

  ngAfterViewInit(): void {}

  getContent(contentType: string): Content {
    return ContentResolver[contentType];
  }

  resolveTitle(contentType: string) {
    this.title = contentType === 'exercises' ? 'Ново упражнение' : 'Нова статия';
  }

  onEditorInit(ev: Editor) {
    this.editor = ev;
    this.content!.patchForm(ev);
  }

  onImageUpload(imageUrl: string) {
    this.content?.onImageUpload(imageUrl);
  }

  onEditorInputChange(ev: string) {
    this.content?.onEditorInputChange(this.editor!, ev);
  }

  onSubmit() {
    const data = this.content?.onFormSubmit(this.editor!);
    if (this.contentType === 'exercises') {
      this.exerciseService.createExercise(data!).subscribe();
    } else {
      this.postService.createPost(data!).subscribe();
    }

    this.form.reset();
  }

  private buildForm() {
    this.form = this.content!.form;
  }

}
