import { Page } from './../../../shared/models/page';
import {
  CreatePostResource,
  PostDetailsResource,
} from 'app/shared/models/post';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { PostViewResource } from 'app/shared/models/post';

@Injectable({
  providedIn: 'root',
})
export class PostService {
  private apiUrl: string = environment.apiUrl + '/posts';

  constructor(private http: HttpClient) {}

  getNewest(): Observable<PostViewResource[]> {
    return this.http.get<PostViewResource[]>(this.apiUrl + '/newest');
  }

  getPopular(): Observable<PostViewResource[]> {
    return this.http.get<PostViewResource[]>(this.apiUrl + '/popular');
  }

  getById(postId: string): Observable<PostDetailsResource> {
    return this.http.get<PostDetailsResource>(
      `${this.apiUrl}/details/${postId}`
    );
  }

  getAll(page: number, size: number): Observable<Page<PostViewResource[]>> {
    return this.http.get<Page<PostViewResource[]>>(`${this.apiUrl}/all`, {
      params: {
        page,
        size,
      },
    });
  }

  getPostsBy(
    itemId: string,
    page: number,
    size: number,
    itemType: string
  ): Observable<Page<PostViewResource[]>> {
    return this.http.get<Page<PostViewResource[]>>(
      `${this.apiUrl}/all/filtered`,
      {
        params: {
          type: itemType,
          id: itemId,
          page,
          size,
        },
      }
    );
  }

  createPost(post: CreatePostResource): Observable<PostViewResource> {
    return this.http.post<PostViewResource>(this.apiUrl, post);
  }
}
