import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { PostViewResource } from 'app/shared/models/post';
import { Observable, map } from 'rxjs';
import { PostQueryType } from 'app/shared/enums/Post';

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

  getPostsBy(
    categoryId: string,
    page: number,
    size: number
  ): Observable<PostViewResource[]> {
    return this.http
      .get<PostViewResource[]>(`${this.apiUrl}/all/${categoryId}`, {
        params: {
          page,
          size,
          type: PostQueryType.Category
        },
      })
      .pipe(
        map((res: any) => {
          return res.content;
        })
      );
  }

  getPostsByTagId(
    tagId: string,
    page: number,
    size: number
  ): Observable<PostViewResource[]> {
    return this.http
      .get<PostViewResource[]>(`${this.apiUrl}/all/${tagId}`, {
        params: {
          page,
          size,
          type: PostQueryType.Tag
        },
      })
      .pipe(
        map((res: any) => {
          return res.content;
        })
      );
  }
}
