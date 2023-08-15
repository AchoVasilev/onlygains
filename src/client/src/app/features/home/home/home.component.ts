import { CategoryService } from 'app/core/services/category/category.service';
import { PostService } from './../../../core/services/post/post.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CategoryViewResource } from 'app/shared/models/category';

@Component({
  selector: 'gains-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  animations: [
    
  ]
})
export class HomeComponent implements OnInit {

  categories$?: Observable<CategoryViewResource[]>;

  constructor(private postService: PostService, private categoryService: CategoryService) {}

  ngOnInit(): void {
    this.categories$ = this.categoryService.getCategories();
  }


}
