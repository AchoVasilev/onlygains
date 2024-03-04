import { Component, Input } from '@angular/core';
import {
  ActiveComment,
  CommentEmittedType,
  CommentViewResource,
} from 'app/shared/models/comment';
import { CommentFormComponent } from '../comment-form/comment-form.component';
import { CommentComponent } from '../comment/comment.component';

@Component({
  selector: 'active-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.scss'],
  standalone: true,
  imports: [CommentComponent, CommentFormComponent],
})
export class CommentsComponent {
  @Input() comments?: CommentViewResource[];
  activeComment!: ActiveComment | null;

  @Input() currentUserId!: string;
  onSubmit(comment: CommentEmittedType): void {
    console.log(comment);
  }

  setActiveComment(comment: ActiveComment | null) {
    this.activeComment = comment;
  }

  updateComment(comment: { text: string; commentId: string }) {
    console.log(comment);
    throw new Error('Method not implemented.');
  }

  deleteComment(commentId: string) {
    console.log(commentId);
    throw new Error('Method not implemented.');
  }

  addComment(comment: { text: string; parentId: string | null }) {
    console.log(comment);
  }

  getRootComments() {
    return this.comments?.filter(comment => comment?.parentId === null);
  }

  getReplies(comment: CommentViewResource): CommentViewResource[] {
    return comment.replies;
  }
}
