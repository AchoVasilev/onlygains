import { Routes } from '@angular/router';

const AUTH_ROUTES: Routes = [
  {
    path: '',
    children: [
      {
        path: 'login',
      },
    ],
  },
];

export default AUTH_ROUTES;
