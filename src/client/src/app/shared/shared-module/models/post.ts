import { CategoryViewResource } from "./category"
import { CommentViewResource } from "./comment"

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
    createdBy: string,
    createdAt: string,
    imageUrls: string[],
    category: CategoryViewResource,
    comments: CommentViewResource[]
}