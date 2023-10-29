import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ContentResolver } from '../content';

@Component({
  selector: 'gains-create-content',
  templateUrl: './create-content.component.html',
  styleUrls: ['./create-content.component.scss']
})
export class CreateContentComponent implements OnInit {

  contentType?: string;

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.contentType = this.route.snapshot.params['type'];
    this.getContent(this.contentType!);
  }

  getContent(contentType: string) {
    return ContentResolver[contentType];
  }
}
