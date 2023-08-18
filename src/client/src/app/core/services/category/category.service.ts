import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { CategoryViewResource } from 'app/shared/models/category';
import { Observable } from 'rxjs';

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
