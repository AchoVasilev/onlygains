import { Component, EventEmitter, Input, Output } from '@angular/core';
import { UserWorkoutProfileDetailsResource } from 'app/shared/models/user';
import { IconButtonComponent } from '../../../../shared/components/buttons/icon-button/icon-button.component';
import { NgIf } from '@angular/common';

@Component({
    selector: 'active-user-details',
    templateUrl: './user-details.component.html',
    styleUrls: ['./user-details.component.scss'],
    standalone: true,
    imports: [NgIf, IconButtonComponent],
})
export class UserDetailsComponent {

  @Input({required: true}) user!: UserWorkoutProfileDetailsResource | undefined;

  @Output() calculateBmi = new EventEmitter<void>();
  @Output() calculateBmr = new EventEmitter<void>();

  onCalculateBmiClick() {
    this.calculateBmi.emit();
  }

  onCalculateBmrClick() {
    this.calculateBmr.emit();
  }
}
