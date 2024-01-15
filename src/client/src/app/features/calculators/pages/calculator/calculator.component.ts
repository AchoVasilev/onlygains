import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SideBarComponent } from 'app/shared/components/side-bar/side-bar.component';

@Component({
  selector: 'active-calculator',
  standalone: true,
  imports: [RouterOutlet, SideBarComponent],
  templateUrl: './calculator.component.html',
  styleUrl: './calculator.component.scss',
})
export class CalculatorComponent {}
