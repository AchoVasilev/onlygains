import { Component } from '@angular/core';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { isNumberValidator } from 'app/shared/validators/number-validator';

@Component({
  selector: 'active-bmi',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
  ],
  templateUrl: './bmi.component.html',
  styleUrl: './bmi.component.scss',
})
export class BmiComponent {
  form = new FormGroup({
    weight: new FormControl<number | null>(null, [
      Validators.required,
      isNumberValidator,
    ]),
    height: new FormControl<number | null>(null, [
      Validators.required,
      isNumberValidator,
    ]),
  });
}
