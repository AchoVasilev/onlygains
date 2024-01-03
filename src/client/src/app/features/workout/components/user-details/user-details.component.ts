import { Component, EventEmitter, Input, Output } from '@angular/core';
import {
  UpdateWorkoutProfileResource,
  UserWorkoutProfileDetailsResource,
} from 'app/shared/models/user';
import { IconButtonComponent } from '../../../../shared/components/buttons/icon-button/icon-button.component';
import { NgIf } from '@angular/common';
import { InlineEditComponent } from 'app/shared/components/inline-edit/inline-edit.component';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'active-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss'],
  standalone: true,
  imports: [NgIf, IconButtonComponent, InlineEditComponent],
})
export class UserDetailsComponent {

  form = new FormGroup({
    weight: new FormControl<number | null>(null, [Validators.min(10), Validators.max(500)]),
    height: new FormControl<number | null>(null, [Validators.min(10), Validators.max(500)]),
    age: new FormControl<number | null>(null, [Validators.min(1), Validators.max(130)]),
    bodyFat: new FormControl<number | null>(null, [Validators.min(1), Validators.max(100)]),
    gender: new FormControl<string | null>(null)
  });

  @Input({ required: true }) user!:
    | UserWorkoutProfileDetailsResource
    | undefined;

  @Output() calculateBmi = new EventEmitter<void>();
  @Output() calculateBmr = new EventEmitter<void>();
  @Output() updateProfile = new EventEmitter<UpdateWorkoutProfileResource>();

  onCalculateBmiClick() {
    this.calculateBmi.emit();
  }

  onCalculateBmrClick() {
    this.calculateBmr.emit();
  }

  onSubmitData() {
    const {weight, height, age, bodyFat, gender} = this.form.value;

    this.updateProfile.emit({weight, height, age, bodyFat, gender});
  }
}
