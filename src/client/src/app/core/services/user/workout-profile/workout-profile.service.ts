import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  UpdateWorkoutProfileResource,
  UserWorkoutProfileDetailsResource,
} from 'app/shared/models/user';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class WorkoutProfileService {
  private apiUrl = environment.apiUrl + '/users';

  constructor(private http: HttpClient) {}

  getById(profileId: string): Observable<UserWorkoutProfileDetailsResource> {
    return this.http.get<UserWorkoutProfileDetailsResource>(
      `${this.apiUrl}/${profileId}/workout-profile`
    );
  }

  updateProfile(
    profileId: string,
    resource: UpdateWorkoutProfileResource
  ): Observable<UserWorkoutProfileDetailsResource> {
    return this.http.patch<UserWorkoutProfileDetailsResource>(
      `${this.apiUrl}/${profileId}/workout-profile`,
      resource
    );
  }
}