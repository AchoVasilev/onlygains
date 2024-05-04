import { NgClass } from '@angular/common';
import {
  Component,
  ElementRef,
  EventEmitter,
  Output,
  ViewChild,
} from '@angular/core';

@Component({
  selector: 'active-panel',
  standalone: true,
  imports: [NgClass],
  templateUrl: './panel.component.html',
  styleUrl: './panel.component.scss',
})
export class PanelComponent {
  @Output()
  viewLogIn = new EventEmitter<void>();

  @Output()
  viewRegister = new EventEmitter<void>();

  @ViewChild('container')
  private panelsContainerElement?: ElementRef<HTMLElement>;

  signUp() {
    this.viewRegister.emit();
    this.panelsContainerElement?.nativeElement?.classList?.add('sign-up-mode');
  }

  signIn() {
    this.viewLogIn.emit();

    this.panelsContainerElement?.nativeElement?.classList?.remove(
      'sign-up-mode'
    );
  }
}
