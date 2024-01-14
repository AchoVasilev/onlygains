import { CategoryViewResource } from './category';
import { CommentViewResource } from './comment';
import { TagViewResource } from './tag';
import { UserViewResource } from './user';

export interface PostViewResource {
  id: string;
  title: string;
  text: string;
  previewText: string;
  createdAt: string;
  createdBy: string;
  creatorImageUrl: string;
  imageUrl: string;
  categoryId: string;
  categoryName: string;
  categoryNameTranslation: string;
}

export interface PostDetailsResource {
  id: string;
  title: string;
  text: string;
  createdBy: UserViewResource;
  createdAt: string;
  imageUrls: string[];
  category: CategoryViewResource;
  comments: CommentViewResource[];
  tags: TagViewResource[];
}

export interface CreatePostResource {
  title?: string | null;
  text?: string | null;
  previewText?: string | null;
  tags?: string[] | null;
  categoryId?: string | null;
  imageUrls?: string[] | null;
}
