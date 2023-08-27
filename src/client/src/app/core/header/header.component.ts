import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'gains-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  standalone: true,
  imports: [MatToolbarModule, MatIconModule, MatButtonModule, RouterLink]
})
export class HeaderComponent {

}
