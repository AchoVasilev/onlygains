import { APP_INITIALIZER, importProvidersFrom } from '@angular/core';
import { AppComponent } from './app/app/app.component';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { provideAnimations } from '@angular/platform-browser/animations';
import { BrowserModule, bootstrapApplication } from '@angular/platform-browser';
import { provideRouter } from '@angular/router';
import { APPLICATION_ROUTES } from 'app/app-routing';
import { authInterceptor } from 'app/core/services/auth/auth.interceptor';
import { initializeAppFactory } from 'app/core/util/app-initialize';
import { AuthService } from 'app/core/services/auth/auth.service';

bootstrapApplication(AppComponent, {
  providers: [
    importProvidersFrom(BrowserModule),
    provideRouter(APPLICATION_ROUTES),
    provideAnimations(),
    provideHttpClient(withInterceptors([authInterceptor])),
    {
      provide: APP_INITIALIZER,
      useFactory: initializeAppFactory,
      multi: true,
      deps: [AuthService],
    },
  ],
}).catch(err => console.error(err));
