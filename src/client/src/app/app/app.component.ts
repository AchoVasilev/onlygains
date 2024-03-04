import { Component } from '@angular/core';
import { FooterComponent } from '../core/footer/footer.component';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from '../core/header/header.component';

@Component({
  selector: 'active-root',
  templateUrl: './app.component.html',
  standalone: true,
  imports: [HeaderComponent, RouterOutlet, FooterComponent],
})
export class AppComponent {}
