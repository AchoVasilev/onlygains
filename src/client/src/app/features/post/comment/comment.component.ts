import { Component, EventEmitter, Input, Output } from '@angular/core';
import {
  ActiveComment,
  ActiveCommentType,
  CommentViewResource,
} from 'app/shared/shared-module/models/comment';

@Component({
  selector: 'gains-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.scss'],
})
export class CommentComponent {
  @Input() comment!: CommentViewResource;
  @Input() activeComment!: ActiveComment | null;
  @Input() currentUserId!: string;
  @Input() parentId!: string | null;

  @Output()
  setActiveComment = new EventEmitter<ActiveComment | null>();
  @Output()
  deleteComment = new EventEmitter<string>();
  @Output()
  addComment = new EventEmitter<{ text: string; parentId: string | null }>();
  @Output()
  updateComment = new EventEmitter<{ text: string; commentId: string }>();

  createdAt: string = '';
  canReply: boolean = false;
  canEdit: boolean = false;
  canDelete: boolean = false;
  activeCommentType = ActiveCommentType;
  replyId: string | null = null;

  ngOnInit(): void {
    this.createdAt = new Date(this.comment.createdAt).toLocaleDateString();
    this.canReply = Boolean(this.currentUserId);
    this.canEdit = this.currentUserId === this.comment.createdBy.id;
    this.canDelete =
      this.currentUserId === this.comment.createdBy.id &&
      this.comment.replies.length === 0;
    this.replyId = this.parentId ? this.parentId : this.comment.id;
  }

  isReplying(): boolean {
    return !this.activeComment
      ? false
      : this.activeComment.id === this.comment.id &&
          this.activeComment.type === this.activeCommentType.replying;
  }

  isEditing(): boolean {
    return !this.activeComment
      ? false
      : this.activeComment.id === this.comment.id &&
          this.activeComment.type === this.activeCommentType.editing;
  }
}
