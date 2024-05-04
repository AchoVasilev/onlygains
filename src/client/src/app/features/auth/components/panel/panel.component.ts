import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'active-panel',
  standalone: true,
  imports: [],
  templateUrl: './panel.component.html',
  styleUrl: './panel.component.scss',
})
export class PanelComponent {
  @Output()
  viewLogIn = new EventEmitter<void>();

  @Output()
  viewRegister = new EventEmitter<void>();

  signUp() {
    this.viewRegister.emit();
  }

  signIn() {
    this.viewLogIn.emit();
  }
}
