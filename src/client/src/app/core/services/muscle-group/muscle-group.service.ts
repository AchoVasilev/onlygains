import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MuscleGroupDetailsResource } from 'app/shared/models/exercise';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MuscleGroupService {

  private apiUrl = environment.apiUrl + '/muscle-groups';

  constructor(private readonly http: HttpClient) { }

  getAll(): Observable<MuscleGroupDetailsResource[]> {
    return this.http.get<MuscleGroupDetailsResource[]>(this.apiUrl);
  }
}
