import { Component, Input } from '@angular/core';
import { MatIconModule, MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'active-social-media',
  standalone: true,
  imports: [MatIconModule],
  templateUrl: './social-media.component.html',
  styleUrl: './social-media.component.scss',
})
export class SocialMediaComponent {
  @Input({ required: true })
  text: string = '';
  constructor(iconRegistry: MatIconRegistry, sanitizer: DomSanitizer) {
    iconRegistry.addSvgIcon(
      'google-icon',
      sanitizer.bypassSecurityTrustResourceUrl('assets/icons/google-icon.svg')
    );
  }
}
