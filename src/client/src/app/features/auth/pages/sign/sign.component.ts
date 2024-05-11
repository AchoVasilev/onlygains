import { Component, ElementRef, ViewChild } from '@angular/core';
import { PanelComponent } from '../../components/panel/panel.component';
import { SocialMediaComponent } from '../../components/social-media/social-media.component';
import { InputFieldComponent } from '../../components/input-field/input-field.component';
import { InputButtonComponent } from '../../components/input-button/input-button.component';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { AuthService } from 'app/core/services/auth/auth.service';
import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { HTTP_ERRORS } from '../../shared/models/validation';
import { LocalStorageService } from 'app/core/services/local-storage/local-storage.service';
import { Router } from '@angular/router';
import { UserService } from 'app/core/services/user/user.service';
import { confirmPasswordValidator } from '../../shared/password.validator';

export type AuthViewType = 'register' | 'login';

@Component({
  selector: 'active-sign',
  standalone: true,
  imports: [
    PanelComponent,
    SocialMediaComponent,
    InputFieldComponent,
    InputButtonComponent,
    ReactiveFormsModule,
  ],
  templateUrl: './sign.component.html',
  styleUrl: './sign.component.scss',
})
export class SignComponent {
  @ViewChild('container')
  containerElement?: ElementRef<HTMLElement>;
  viewType: AuthViewType = 'login';

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
  });

  registerForm = new FormGroup(
    {
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required]),
      repeatPassword: new FormControl('', [Validators.required]),
    },
    { validators: confirmPasswordValidator }
  );

  errors: string[] = [];

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private localStorageService: LocalStorageService,
    private router: Router
  ) {}

  onViewRegister() {
    this.viewType = 'register';
    this.containerElement?.nativeElement?.classList?.add('sign-up-mode');
    this.loginForm.reset();
  }

  onViewLogin() {
    this.viewType = 'login';
    this.containerElement?.nativeElement?.classList?.remove('sign-up-mode');
    this.registerForm.reset();
  }

  onLogin() {
    const { email, password } = this.loginForm.value;
    this.authService.login({ email, password }).subscribe({
      next: response => {
        this.localStorageService.setItem(
          response.tokenType,
          response.accessToken
        );

        this.localStorageService.setItem(
          'refresh_token',
          response.refreshToken
        );

        this.authService.currentUserSignal.set({
          username: response.username,
          roles: response.roles,
        });

        this.router.navigateByUrl('/');
      },
      error: (err: HttpErrorResponse) => {
        if (
          err.status === HttpStatusCode.BadRequest ||
          err.status === HttpStatusCode.NotFound
        ) {
          const errExtensions = err.error.extensions;
          Object.keys(errExtensions).forEach(key => {
            const errorMessages = HTTP_ERRORS.get(key)?.filter(
              e => e === errExtensions[key]
            );

            if (errorMessages) {
              this.errors = [...errorMessages];
            }
          });
        }
      },
    });
  }

  onRegister() {}

  passwordsNotMatch(): boolean {
    return this.registerForm.errors !== null
      ? this.registerForm.errors['PasswordsNotMatch']
      : false;
  }
}
