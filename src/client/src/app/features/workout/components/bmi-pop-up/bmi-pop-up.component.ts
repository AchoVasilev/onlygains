import { Component, EventEmitter } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { KeyValuePipe, NgFor } from '@angular/common';
import { BmiFormula } from 'app/shared/models/body-mass';

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
  ],
  templateUrl: './bmi-pop-up.component.html',
  styleUrls: ['./bmi-pop-up.component.scss'],
})
export class BmiPopUpComponent {
  bmiFormulas = new Map<string, BmiFormula>([
    ['MIFFLIN_ST_JEOR', { name: 'Мифлин-Сейнт Жур', description: `` }],
    ['KATCH_MCARDLE', { name: 'Кач-МакАрдъл', description: `` }],
    [
      'REVISED_HARRIS_BENEDICT',
      {
        name: 'Ревизирана формула Харис-Бенедикт',
        description: ``,
      },
    ],
  ]);

  constructor(public dialogRef: MatDialogRef<BmiPopUpComponent>) {}

  onGenderAdd = new EventEmitter();
  onBodyFatAdd = new EventEmitter();

  bmiFormula?: BmiFormula;

  onNoClick(): void {
    this.dialogRef.close();
  }

  onFocusOut() {}

  onEnterPress(event: Event) {
    const target = event.target as HTMLInputElement;
    target.blur();
  }
}
