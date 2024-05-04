import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { PanelComponent } from '../../components/panel/panel.component';
import { SocialMediaComponent } from '../../components/social-media/social-media.component';
import { LoginComponent } from '../../components/login/login.component';
import { RegisterComponent } from '../../components/register/register.component';

type AuthViewType = 'register' | 'login';

@Component({
  selector: 'active-sign',
  standalone: true,
  imports: [
    MatIconModule,
    PanelComponent,
    SocialMediaComponent,
    LoginComponent,
    RegisterComponent,
  ],
  templateUrl: './sign.component.html',
  styleUrl: './sign.component.scss',
})
export class SignComponent {
  viewType: AuthViewType = 'register';

  onViewRegister() {
    this.viewType = 'register';
  }

  onViewLogin() {
    this.viewType = 'login';
  }
}
