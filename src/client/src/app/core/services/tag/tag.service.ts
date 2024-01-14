import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TagViewResource } from 'app/shared/models/tag';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class TagService {
  private apiUrl: string = environment.apiUrl + '/tags';

  constructor(private http: HttpClient) {}

  getTags(): Observable<TagViewResource[]> {
    return this.http.get<TagViewResource[]>(this.apiUrl);
  }
}
