import { Component } from '@angular/core';
import { InputFieldComponent } from '../input-field/input-field.component';
import { SocialMediaComponent } from '../social-media/social-media.component';
import { InputButtonComponent } from '../input-button/input-button.component';

@Component({
  selector: 'active-login',
  standalone: true,
  imports: [InputFieldComponent, SocialMediaComponent, InputButtonComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {}
