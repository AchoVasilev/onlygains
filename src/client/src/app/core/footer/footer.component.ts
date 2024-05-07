import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule, MatIconRegistry } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'active-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss'],
  standalone: true,
  imports: [MatToolbarModule, MatIconModule, MatButtonModule],
})
export class FooterComponent {
  constructor(
    private iconRegistry: MatIconRegistry,
    private sanitizer: DomSanitizer
  ) {
    this.iconRegistry.addSvgIcon(
      'facebook-icon',
      this.sanitizer.bypassSecurityTrustResourceUrl(
        'assets/icons/facebook-icon.svg'
      )
    );

    iconRegistry.addSvgIcon(
      'instagram-icon',
      this.sanitizer.bypassSecurityTrustResourceUrl(
        'assets/icons/instagram-icon.svg'
      )
    );

    this.iconRegistry.addSvgIcon(
      'google-icon',
      this.sanitizer.bypassSecurityTrustResourceUrl(
        'assets/icons/google-icon.svg'
      )
    );
  }
}
