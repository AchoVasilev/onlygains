import { Component, Input } from '@angular/core';
import {
  ActiveComment,
  CommentEmittedType,
} from 'app/shared/shared-module/models/comment';
import { PostDetailsResource } from 'app/shared/shared-module/models/post';

@Component({
  selector: 'gains-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.scss'],
})
export class CommentsComponent {
  @Input() post?: PostDetailsResource;
  activeComment!: ActiveComment | null;

  @Input() currentUserId!: string;
  onSubmit(comment: CommentEmittedType): void {}

  setActiveComment(comment: ActiveComment | null) {
    this.activeComment = comment;
  }

  updateComment($event: { text: string; commentId: string }) {
    throw new Error('Method not implemented.');
  }

  deleteComment($event: string) {
    throw new Error('Method not implemented.');
  }
  
  addComment($event: { text: string; parentId: string | null }) {
    throw new Error('Method not implemented.');
  }
}
