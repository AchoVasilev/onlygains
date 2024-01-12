import { Component, Input } from '@angular/core';
import { NgClass, NgIf } from '@angular/common';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatIconModule, MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';

const QUESTION_ICON = `<svg
xmlns="http://www.w3.org/2000/svg"
width="20"
height="20"
fill="currentColor"
viewBox="0 0 256 256"
>
<rect width="256" height="256" fill="none"></rect>
<circle
  cx="128"
  cy="128"
  r="96"
  fill="none"
  stroke="currentColor"
  stroke-linecap="round"
  stroke-linejoin="round"
  stroke-width="16"
></circle>
<circle cx="128" cy="180" r="12"></circle>
<path
  d="M127.9995,144.0045v-8a28,28,0,1,0-28-28"
  fill="none"
  stroke="currentColor"
  stroke-linecap="round"
  stroke-linejoin="round"
  stroke-width="16"
></path>
</svg>`;

@Component({
  selector: 'active-tooltip',
  standalone: true,
  imports: [NgIf, NgClass, MatTooltipModule, MatIconModule],
  templateUrl: './tooltip.component.html',
  styleUrls: ['./tooltip.component.scss'],
})
export class TooltipComponent {
  @Input({ required: true }) text?: string;
  @Input() position: 'left' | 'right' | 'above' | 'below' | 'before' | 'after' = 'right';

  constructor(
    private iconRegistry: MatIconRegistry,
    private sanitizer: DomSanitizer
  ) {
    this.iconRegistry.addSvgIconLiteral(
      'question-mark',
      this.sanitizer.bypassSecurityTrustHtml(QUESTION_ICON)
    );
  }
}
