import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { isNumberValidator } from 'app/shared/validators/number-validator';

@Component({
  selector: 'active-bmi',
  standalone: true,
  imports: [],
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
