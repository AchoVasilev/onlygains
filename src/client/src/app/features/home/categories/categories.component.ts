import { Component, Input } from '@angular/core';
import { CategoryViewResource } from 'app/shared/shared-module/models/category';

@Component({
  selector: 'gains-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent {
  @Input()
  categories?: CategoryViewResource[] | null;
}
