import { Component, Input } from '@angular/core';
import { CategoryViewResource } from 'app/shared/models/category';
import { RouterLink } from '@angular/router';

@Component({
    selector: 'active-categories',
    templateUrl: './categories.component.html',
    styleUrls: ['./categories.component.scss'],
    standalone: true,
    imports: [RouterLink]
})
export class CategoriesComponent {
  @Input()
  categories?: CategoryViewResource[] | null;
}
