import { HttpClient } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import {
  LoginResource,
  AccessTokenResponseResource,
  UserModel,
  RefreshTokenResponseResource,
} from 'app/features/auth/shared/models/auth-models';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';
import { LocalStorageService } from '../local-storage/local-storage.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = environment.apiUrl + '/auth';

  constructor(
    private http: HttpClient,
    private localStorageService: LocalStorageService
  ) {}

  login(resource: LoginResource): Observable<AccessTokenResponseResource> {
    return this.http.post<AccessTokenResponseResource>(
      this.apiUrl + '/login',
      resource
    );
  }

  refreshToken(token: string): Observable<RefreshTokenResponseResource> {
    return this.http.post<RefreshTokenResponseResource>(
      this.apiUrl + '/refresh',
      {
        grant_type: 'refresh-token',
        refresh_token: token,
      }
    );
  }

  currentUserSignal = signal<UserModel | null | undefined>(null);
  isRefreshingTokenSignal = signal<boolean>(false);

  isLoggedIn() {
    return (
      this.currentUserSignal() !== null &&
      this.currentUserSignal() !== undefined &&
      !!this.localStorageService.getItem('Bearer')
    );
  }

  clearLocalData() {
    this.localStorageService.removeItem('Bearer');
    this.localStorageService.removeItem('refresh_token');
    this.currentUserSignal.set(null);
  }
}
