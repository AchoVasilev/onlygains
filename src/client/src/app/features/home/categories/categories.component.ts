import { Component, Input } from '@angular/core';
import { CategoryViewResource } from 'app/shared/models/category';
import { RouterLink } from '@angular/router';
import { NgFor } from '@angular/common';

@Component({
    selector: 'active-categories',
    templateUrl: './categories.component.html',
    styleUrls: ['./categories.component.scss'],
    standalone: true,
    imports: [NgFor, RouterLink]
})
export class CategoriesComponent {
  @Input()
  categories?: CategoryViewResource[] | null;
}
