import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { PostViewResource } from 'app/shared/models/post';
import { Observable, map } from 'rxjs';
import { TagViewResource } from 'app/shared/models/tag';

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
    categoryName: string,
    categoryId: string,
    currentPage: number
  ): Observable<PostViewResource[]> {
    return this.http
      .get<PostViewResource[]>(`${this.apiUrl}/${categoryName}/${categoryId}`, {
        params: {
          currentPage,
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
    currentPage: number
  ): Observable<PostViewResource[]> {
    return this.http
      .get<PostViewResource[]>(`${this.apiUrl}/tags/${tagId}`, {
        params: {
          currentPage,
        },
      })
      .pipe(
        map((res: any) => {
          return res.content;
        })
      );
  }
}
