import { Component, EventEmitter, Input, Output } from '@angular/core';
import {
  UpdateWorkoutProfileResource,
  UserWorkoutProfileDetailsResource,
} from 'app/shared/models/user';
import { IconButtonComponent } from '../../../../shared/components/buttons/icon-button/icon-button.component';
import { NgIf } from '@angular/common';
import { EditData, InlineEditComponent } from 'app/shared/components/inline-edit/inline-edit.component';

@Component({
  selector: 'active-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss'],
  standalone: true,
  imports: [NgIf, IconButtonComponent, InlineEditComponent],
})
export class UserDetailsComponent {
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

  onSubmitData(data: EditData) {
    console.log(data)
    let resource: UpdateWorkoutProfileResource = {};

    switch (data.key) {
      case 'weight':
        resource.weight = data.value as number;
        break;
      case 'height':
        resource.height = data.value as number;
        break;
    }

    this.updateProfile.emit(resource);
  }
}
