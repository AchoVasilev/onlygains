import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  LoginResource,
  TokenResponseResource,
} from 'app/features/auth/models/auth-models';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = environment.apiUrl + '/auth';

  constructor(private http: HttpClient) {}

  login(resource: LoginResource): Observable<TokenResponseResource> {
    return this.http.post<TokenResponseResource>(
      this.apiUrl + '/login',
      resource
    );
  }
}
