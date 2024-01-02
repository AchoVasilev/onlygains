import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CreateBmiResource } from 'app/shared/models/body-mass';
import { UserWorkoutProfileDetailsResource } from 'app/shared/models/user';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class BodyMassService {

  private apiUrl = environment.apiUrl + '/body-mass';

  constructor(private http: HttpClient) {}

  calculateBmi(workoutProfileId: string, resource: CreateBmiResource): Observable<UserWorkoutProfileDetailsResource> {
    return this.http.post<UserWorkoutProfileDetailsResource>(`${this.apiUrl}/bmi/${workoutProfileId}`, resource);
  }
}
