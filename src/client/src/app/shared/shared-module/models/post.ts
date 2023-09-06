import { CategoryViewResource } from "./category"
import { CommentViewResource } from "./comment"
import { TagViewResource } from "./tag"
import { UserViewResource } from "./user"

export interface PostViewResource {
    id: string,
    title: string,
    text: string,
    createdAt: string,
    createdBy: string,
    creatorImageUrl: string,
    imageUrl: string,
    categoryId: string,
    categoryName: string,
    categoryNameTranslation: string
}

export interface PostDetailsResource {
    id: string,
    title: string, 
    text: string,
    createdBy: UserViewResource,
    createdAt: string,
    imageUrls: string[],
    category: CategoryViewResource,
    comments: CommentViewResource[],
    tags: TagViewResource[]
}

export interface CreatePostResource {
    title: string,
    text: string,
    tags: string[],
    categoryId: string,
    imageUrls: string[]
}