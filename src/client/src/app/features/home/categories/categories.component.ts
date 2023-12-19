import { Component, Input } from '@angular/core';
import { CategoryViewResource } from 'app/shared/models/category';

@Component({
  selector: 'active-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent {
  @Input()
  categories?: CategoryViewResource[] | null;
}
