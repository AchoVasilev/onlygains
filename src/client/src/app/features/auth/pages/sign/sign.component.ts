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
import { HTTP_ERRORS, PASSWORDS_REGEX } from '../../shared/models/validation';
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
      password: new FormControl('', [
        Validators.required,
        Validators.pattern(PASSWORDS_REGEX),
      ]),
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
    this.errors = [];
  }

  onViewLogin() {
    this.viewType = 'login';
    this.containerElement?.nativeElement?.classList?.remove('sign-up-mode');
    this.registerForm.reset();
    this.errors = [];
  }

  onLogin() {
    const { email, password } = this.loginForm.value;
    this.authService.login({ email, password }).subscribe({
      next: () => {
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

  onRegister() {
    const user = this.registerForm.value;

    this.userService.createUser(user).subscribe();
  }

  passwordsNotMatch(): boolean {
    return this.registerForm.errors !== null &&
      this.registerForm.controls.password.touched &&
      this.registerForm.controls.password.dirty &&
      this.registerForm.controls.repeatPassword.touched &&
      this.registerForm.controls.repeatPassword.dirty
      ? this.registerForm.errors['PasswordsNotMatch']
      : false;
  }
}
