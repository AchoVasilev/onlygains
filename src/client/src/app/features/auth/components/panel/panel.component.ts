import { NgClass } from '@angular/common';
import {
  ChangeDetectionStrategy,
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
  changeDetection: ChangeDetectionStrategy.OnPush,
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

  isRegister = false;

  signUp() {
    this.viewRegister.emit();
    this.isRegister = false;
    this.panelsContainerElement?.nativeElement?.classList?.add('sign-up-mode');
  }

  signIn() {
    this.viewLogIn.emit();
    this.isRegister = true;
    this.panelsContainerElement?.nativeElement?.classList?.remove(
      'sign-up-mode'
    );
  }
}
