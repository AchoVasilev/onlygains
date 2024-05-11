import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegisterUserRequestResource } from 'app/features/auth/shared/models/auth-models';
import { environment } from 'environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl = environment.apiUrl + '/users';

  constructor(private http: HttpClient) {}

  createUser(user: RegisterUserRequestResource) {
    return this.http.post(this.apiUrl, user);
  }
}
