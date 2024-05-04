import { Routes } from '@angular/router';
import { SignComponent } from './pages/sign/sign.component';

const AUTH_ROUTES: Routes = [
  {
    path: '',
    component: SignComponent,
  },
];

export default AUTH_ROUTES;
