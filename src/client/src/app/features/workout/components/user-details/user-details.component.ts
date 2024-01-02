import { Component, EventEmitter, Input, Output } from '@angular/core';
import { UserWorkoutProfileDetailsResource } from 'app/shared/models/user';

@Component({
  selector: 'active-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss'],
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
