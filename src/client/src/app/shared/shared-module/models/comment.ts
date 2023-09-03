import { UserViewResource } from "./user";

export interface CommentViewResource {
  id: string;
  createdBy: UserViewResource;
  createdAt: string;
  text: string;
  replies: CommentViewResource[];
}

export interface CommentEmittedType {
  text: string,
  commentId: string | null
}

export interface ActiveComment {
  id: string;
  type: ActiveCommentType;
}

export enum ActiveCommentType {
  replying = 'replying',
  editing = 'editing',
  default = 'default',
}