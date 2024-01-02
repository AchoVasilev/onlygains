import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';

const HOME_ROUTES: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full',
    title: 'MyActivePal - Начало',
  },
];

export default HOME_ROUTES;