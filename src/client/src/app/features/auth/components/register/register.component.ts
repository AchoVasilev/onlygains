import { Component } from '@angular/core';
import { SocialMediaComponent } from '../social-media/social-media.component';
import { InputFieldComponent } from '../input-field/input-field.component';
import { InputButtonComponent } from '../input-button/input-button.component';

@Component({
  selector: 'active-register',
  standalone: true,
  imports: [SocialMediaComponent, InputFieldComponent, InputButtonComponent],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
})
export class RegisterComponent {}
