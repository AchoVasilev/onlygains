import { Routes } from '@angular/router';
import { CreateContentComponent } from './create-content/create-content.component';

const CONTENT_CREATION_ROUTES: Routes = [
  {
    path: '',
    component: CreateContentComponent,
  },
];

export default CONTENT_CREATION_ROUTES;
