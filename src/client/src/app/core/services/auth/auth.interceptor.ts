import {
  HttpErrorResponse,
  HttpInterceptorFn,
  HttpRequest,
  HttpStatusCode,
} from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from './auth.service';
import { LocalStorageService } from '../local-storage/local-storage.service';
import {
  catchError,
  throwError,
  switchMap,
  Subject,
  filter,
  take,
  finalize,
} from 'rxjs';
import { Router } from '@angular/router';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const localStorageService = inject(LocalStorageService);
  const token = localStorageService.getItem('Bearer') ?? '';
  const tokenSubject$ = new Subject<string | null>();

  const authService = inject(AuthService);
  const router = inject(Router);

  const refreshToken = localStorageService.getItem('refresh_token') ?? '';

  req = addTokenToRequest(req, token);

  return next(req).pipe(
    catchError((err: HttpErrorResponse) => {
      if (err.status === HttpStatusCode.Unauthorized) {
        if (!authService.isRefreshingTokenSignal()) {
          authService.isRefreshingTokenSignal.set(true);
          tokenSubject$.next(null);

          return authService.refreshToken(refreshToken).pipe(
            switchMap(response => {
              if (response.accessToken) {
                localStorageService.setItem('Bearer', response.accessToken);
                localStorageService.setItem(
                  'refresh_token',
                  response.refreshToken
                );

                tokenSubject$.next(response.accessToken);

                return next(addTokenToRequest(req, response.accessToken));
              }

              authService.clearLocalData();
              router.navigateByUrl('/auth');

              return throwError(() => Error(''));
            }),
            catchError(() => {
              authService.clearLocalData();
              router.navigateByUrl('/auth');

              return throwError(() => Error(''));
            }),
            finalize(() => {
              authService.isRefreshingTokenSignal.set(false);
            })
          );
        }

        return tokenSubject$.pipe(
          filter(token => token !== null),
          take(1),
          switchMap(token => {
            return next(addTokenToRequest(req, token!));
          })
        );
      }

      return throwError(() => err);
    })
  );
};

export const addTokenToRequest = (req: HttpRequest<unknown>, token: string) => {
  return req.clone({
    setHeaders: {
      Authorization: `Bearer ${token}`,
    },
  });
};
