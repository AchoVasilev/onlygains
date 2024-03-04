import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterLink } from '@angular/router';
import { CategoryService } from '../services/category/category.service';
import { Observable } from 'rxjs';
import { CategoryViewResource } from 'app/shared/models/category';
import { AsyncPipe } from '@angular/common';
import { CategoryComponent } from 'app/shared/components/category/category.component';

@Component({
  selector: 'active-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  standalone: true,
  imports: [
    AsyncPipe,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    RouterLink,
    CategoryComponent,
  ],
})
export class HeaderComponent implements OnInit {
  categories$?: Observable<CategoryViewResource[]>;

  constructor(private categoryService: CategoryService) {}

  ngOnInit(): void {
    this.categories$ = this.categoryService.getCategories();
  }
}
