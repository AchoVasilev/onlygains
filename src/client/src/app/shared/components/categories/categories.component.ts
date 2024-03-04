import { Component, Input } from '@angular/core';
import { CategoryViewResource } from 'app/shared/models/category';
import { CategoryComponent } from '../category/category.component';

@Component({
  selector: 'active-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss'],
  standalone: true,
  imports: [CategoryComponent],
})
export class CategoriesComponent {
  @Input({ required: true })
  categories?: CategoryViewResource[] | null;
}
