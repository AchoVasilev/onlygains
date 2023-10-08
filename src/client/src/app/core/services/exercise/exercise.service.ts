import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { CreateExerciseResource, ExerciseDetailsResource } from 'app/shared/models/exercise';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExerciseService {
  private apiUrl: string = environment.apiUrl + '/exercises';

  constructor(private http: HttpClient) { }

  getById(id: string): Observable<ExerciseDetailsResource> {
    return this.http.get<ExerciseDetailsResource>(`${this.apiUrl}/${id}`);
  }

  createExercise(data: CreateExerciseResource): Observable<ExerciseDetailsResource> {
    return this.http.post<ExerciseDetailsResource>(this.apiUrl, data);
  }
}
