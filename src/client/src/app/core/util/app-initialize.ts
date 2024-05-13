import { Observable, tap } from 'rxjs';
import { AuthService } from '../services/auth/auth.service';

export function initializeAppFactory(
  authService: AuthService
): () => Observable<any> {
  return () =>
    authService
      .getLogin()
      .pipe(tap(user => authService.currentUserSignal.set(user)));
}
