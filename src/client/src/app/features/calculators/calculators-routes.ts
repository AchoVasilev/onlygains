import type { Routes } from '@angular/router';
import { CalculatorComponent } from './pages/calculator/calculator.component';
import { BmiComponent } from './components/bmi/bmi.component';

const CALCULATORS_ROUTES: Routes = [
  {
    path: '',
    component: CalculatorComponent,
    children: [
      {
        path: '',
        redirectTo: 'bmi',
        pathMatch: 'full',
      },
      {
        path: 'bmi',
        component: BmiComponent,
        pathMatch: 'full',
        title: 'MyActivePal - BMI калкулатор',
      },
      {
        path: 'bmr',
        title: 'MyActivePal - BMR калкулатор',
        pathMatch: 'full',
        loadComponent: () =>
          import('./components/bmr/bmr.component').then(c => c.BmrComponent),
      },
    ],
  },
];

export default CALCULATORS_ROUTES;
