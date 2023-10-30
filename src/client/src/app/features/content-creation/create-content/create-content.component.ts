import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Content, ContentResolver } from '../content';
import { Editor } from 'tinymce';
import { FormGroup } from '@angular/forms';

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

  constructor(private route: ActivatedRoute) {}

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
    this.title = contentType === 'exercise' ? 'Ново упражнение' : 'Нова статия';
  }

  onEditorInit(ev: Editor) {
    this.editor = ev;
    this.content!.patchForm(ev);
  }

  private buildForm() {
    this.form = this.content!.form;
  }
}
