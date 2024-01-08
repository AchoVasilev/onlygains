import { Component, EventEmitter } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { KeyValuePipe, NgFor } from '@angular/common';
import { BmiFormula } from 'app/shared/models/body-mass';
import { TooltipComponent } from 'app/shared/components/tooltip/tooltip.component';

@Component({
  selector: 'active-bmi-pop-up',
  standalone: true,
  imports: [
    NgFor,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatButtonModule,
    MatDialogModule,
    MatRadioModule,
    KeyValuePipe,
    TooltipComponent,
  ],
  templateUrl: './bmi-pop-up.component.html',
  styleUrls: ['./bmi-pop-up.component.scss'],
})
export class BmiPopUpComponent {
  bmiFormulas = new Map<string, BmiFormula>([
    [
      'MIFFLIN_ST_JEOR',
      {
        name: 'Мифлин-Сейнт Жур',
        description: `Изчисления:
        Мъже:  BMR =  (10 x тегло в кг) + (6,25 x ръст в см) – (5 x възраст в години) + 5
        Жени:  BMR =  (10 x тегло в кг) + (6,25 x ръст в см) - (5 x възраст в години) - 161`,
      },
    ],
    [
      'KATCH_MCARDLE',
      {
        name: 'Кач-МакАрдъл',
        description: `Изчисления:
        Кеч-МакАрдъл: BMR = 370 + (21,6 x активното тегло в кг)`,
      },
    ],
    [
      'REVISED_HARRIS_BENEDICT',
      {
        name: 'Ревизирана формула Харис-Бенедикт',
        description: `Изчисления:
        Мъже:  BMR = 88,362 + (13,397 x тегло в кг) + (4,799 x ръст в см) – (5,677 x възраст в години)
        Жени:  BMR = 447,593 + (9,247 x тегло в кг) + (3,098 x ръст в см) - (4,330 x възраст в години)`,
      },
    ],
  ]);

  constructor(public dialogRef: MatDialogRef<BmiPopUpComponent>) {}

  onGenderAdd = new EventEmitter();
  onBodyFatAdd = new EventEmitter();

  bmiFormula?: string;
  gender?: string;

  onBmiFormulaChange(ev: string) {
    console.log(ev);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onFocusOut() {}

  onEnterPress(event: Event) {
    const target = event.target as HTMLInputElement;
    target.blur();
  }
}
