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
import { HTTP_ERRORS } from '../../models/validation';

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

  registerForm = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
    repeatPassword: new FormControl('', [Validators.required]),
  });

  errors: string[] = [];

  constructor(private authService: AuthService) {}

  onViewRegister() {
    this.viewType = 'register';
    this.containerElement?.nativeElement?.classList?.add('sign-up-mode');
  }

  onViewLogin() {
    this.viewType = 'login';
    this.containerElement?.nativeElement?.classList?.remove('sign-up-mode');
  }

  onLogin() {
    const { email, password } = this.loginForm.value;
    this.authService.login({ email, password }).subscribe({
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

        console.log(this.errors);
      },
    });
  }
}
