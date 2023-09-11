import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../../../environments/environment';
import { CategoryViewResource } from 'app/shared/shared-module/models/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private apiUrl: string = environment.apiUrl + '/categories';

  constructor(private http: HttpClient) { }

  getCategories(): Observable<CategoryViewResource[]> {
    return this.http.get<CategoryViewResource[]>(this.apiUrl);
  }
}
