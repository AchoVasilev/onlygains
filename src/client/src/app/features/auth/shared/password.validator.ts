import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export const confirmPasswordValidator: ValidatorFn = (
  control: AbstractControl
): ValidationErrors | null => {
  return (control.value.password && (control.dirty || control.touched)) ===
    (control.value.repeatPassword && (control.dirty || control.touched))
    ? null
    : { PasswordsNotMatch: true };
};
