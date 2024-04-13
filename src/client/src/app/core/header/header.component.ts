import {
  Component,
  ElementRef,
  OnDestroy,
  OnInit,
  ViewChild,
} from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { NavigationStart, Router, RouterLink } from '@angular/router';
import { CategoryService } from '../services/category/category.service';
import { Observable, Subject, filter, takeUntil } from 'rxjs';
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
export class HeaderComponent implements OnInit, OnDestroy {
  categories$?: Observable<CategoryViewResource[]>;
  private destroy$: Subject<void> = new Subject();

  @ViewChild('categories') categoriesElement?: ElementRef<HTMLElement>;

  constructor(
    private categoryService: CategoryService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.categories$ = this.categoryService.getCategories();
    this.router.events
      .pipe(
        filter(event => event instanceof NavigationStart),
        takeUntil(this.destroy$)
      )
      .subscribe(() => this.hideItems());
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  showCategories() {
    this.categoriesElement?.nativeElement?.classList.remove('hidden');
  }

  hideCategories() {
    this.categoriesElement?.nativeElement?.classList.add('hidden');
  }

  hideItems() {
    this.hideCategories();
  }
}
