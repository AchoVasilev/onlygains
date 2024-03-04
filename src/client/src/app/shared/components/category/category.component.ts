import { Component, Input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CategoryViewResource } from 'app/shared/models/category';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'active-category',
  standalone: true,
  imports: [RouterLink, MatCardModule],
  templateUrl: './category.component.html',
  styleUrl: './category.component.scss',
})
export class CategoryComponent {
  @Input({ required: true })
  category?: CategoryViewResource | null;
}
