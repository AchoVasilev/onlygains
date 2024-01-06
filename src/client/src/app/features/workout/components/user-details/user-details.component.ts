import { TooltipComponent } from './../../../../shared/components/tooltip/tooltip.component';
import {
  Component,
  ElementRef,
  EventEmitter,
  Input,
  Output,
  ViewChild,
} from '@angular/core';
import {
  UpdateWorkoutProfileResource,
  UserWorkoutProfileDetailsResource,
} from 'app/shared/models/user';
import { IconButtonComponent } from '../../../../shared/components/buttons/icon-button/icon-button.component';
import { NgIf } from '@angular/common';
import { InlineEditComponent } from 'app/shared/components/inline-edit/inline-edit.component';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { isNumberValidator } from 'app/shared/validators/number-validator';

@Component({
  selector: 'active-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss'],
  standalone: true,
  imports: [NgIf, IconButtonComponent, InlineEditComponent, TooltipComponent],
})
export class UserDetailsComponent {
  @ViewChild('weight') private weightElement?: ElementRef<HTMLElement>;
  @ViewChild('height') private heightElement?: ElementRef<HTMLElement>;
  @ViewChild('age') private ageElement?: ElementRef<HTMLElement>;

  form = new FormGroup({
    weight: new FormControl<number | null>(null, [
      isNumberValidator(),
      Validators.min(10),
      Validators.max(500),
    ]),
    height: new FormControl<number | null>(null, [
      isNumberValidator(),
      Validators.min(10),
      Validators.max(300),
    ]),
    age: new FormControl<number | null>(null, [
      isNumberValidator(),
      Validators.min(1),
      Validators.max(130),
    ]),
    bodyFat: new FormControl<number | null>(null, [
      isNumberValidator(),
      Validators.min(1),
      Validators.max(100),
    ]),
    gender: new FormControl<string | null>(null),
  });

  @Input({ required: true }) user!:
    | UserWorkoutProfileDetailsResource
    | undefined;

  @Output() calculateBmi = new EventEmitter<void>();
  @Output() calculateBmr = new EventEmitter<void>();
  @Output() updateProfile = new EventEmitter<UpdateWorkoutProfileResource>();

  onCalculateBmiClick() {
    const validWeight = !!this.user?.weight?.weight;
    const validHeight = !!this.user?.height?.height;

    if (validWeight && validHeight) {
      this.calculateBmi.emit();
      return;
    }

    this.handleFields(validWeight, validHeight, false);
  }

  onCalculateBmrClick() {
    const validWeight = !!this.user?.weight?.weight;
    const validHeight = !!this.user?.height?.height;
    const validAge = !!this.user?.age;

    if (validWeight && validHeight && validAge) {
      this.calculateBmr.emit();
      return;
    }

    this.handleFields(validWeight, validHeight, validAge);
  }

  onSubmitData() {
    const { weight, height, age, bodyFat, gender } = this.form.value;

    this.handleFields(!!weight, !!height, !!age);

    this.updateProfile.emit({ weight, height, age, bodyFat, gender });
  }

  private handleFields(
    validWeight: boolean,
    validHeight: boolean,
    validAge?: boolean
  ) {
    validWeight
      ? this.removeErrorClass(this.weightElement)
      : this.addErrorClass(this.weightElement);

    validHeight
      ? this.removeErrorClass(this.heightElement)
      : this.addErrorClass(this.heightElement);

    validAge
      ? this.removeErrorClass(this.ageElement)
      : this.addErrorClass(this.ageElement);
  }

  private addErrorClass(element?: ElementRef<HTMLElement>) {
    element?.nativeElement.classList.add('error');
  }

  private removeErrorClass(element?: ElementRef<HTMLElement>) {
    element?.nativeElement.classList.remove('error');
  }

  get bmiText() {
    return `BMI (Body Mass Index) е проста калкулация, която използва ръста и теглото на човека.
      BMI резултат от 25.0 и нагоре се счита за надномрено тегло, а за здравословен се счита между 18.5 и 24.9.
      BMI не се използва при трениращите хора, бременните, възрастните или децата.
      Това е така, защото BMI не взима предвид дали теглото е мускулна маса или подкожна мазнина. 
      Хората с по-голям процент мускулна маса, като атлетите, могат да имат по-висок BMI, но да не са в рисково здравословно състояние.
      Тези с по-нисък процент мускулна маса, като децата, които все още израстват или възрастните хора, които може да изгубят мускулна маса, могат да имат по нисък BMI.
      По време на бременност и лактация композицията на женското тяло се променя и използването на BMI не е подходящо.`;
  }

  get bmrText() {
    return `BMR е минималното количество енергия, нужна на тялото ти, за да поддържа основните си функции като дишане, кръвообращение, производство на хормони, клетъчно възстановяване и др…
    BMR се смята по формула, в която влизат величините за пол, тегло, височина и възраст. 
    Но не е нужно да знаеш формулите наизуст, за да проверяваш енергийните си нужди всеки път, когато режимът ти има нужда от промяна. 
    Вместо това можеш да използваш калорийния калкулатор, който ще сметне базовия ти метаболизъм вместо теб.
    Н.Б: Дори и с калориен калкулатор, сметката за BMR е по-скоро добро предположение, от колкото точна величина. 
    Причината е, че той се променя въз основа на различни фактори, включително начинът ти на хранене, условията на околната среда, активност, стрес, нива на сън и др…`;
  }
}
