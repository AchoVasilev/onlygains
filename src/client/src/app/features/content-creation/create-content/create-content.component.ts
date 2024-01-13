import { Component, OnInit } from '@angular/core';
import { MatTabsModule } from '@angular/material/tabs';
import { CreatePostComponent } from '../create-post/create-post.component';
import { CreateExerciseComponent } from '../create-exercise/create-exercise.component';
import { EquipmentService } from 'app/core/services/equipment/equipment.service';
import { ExerciseService } from 'app/core/services/exercise/exercise.service';
import { MuscleGroupService } from 'app/core/services/muscle-group/muscle-group.service';
import { EquipmentResource } from 'app/shared/models/equipment';
import { Observable } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import {
  CreateExerciseResource,
  ExerciseResource,
  MuscleGroupDetailsResource,
} from 'app/shared/models/exercise';
import { CategoryService } from 'app/core/services/category/category.service';
import { PostService } from 'app/core/services/post/post.service';
import { TagService } from 'app/core/services/tag/tag.service';
import { CategoryViewResource } from 'app/shared/models/category';
import { TagViewResource } from 'app/shared/models/tag';
import { CreatePostResource } from 'app/shared/models/post';

@Component({
  selector: 'active-create-content',
  standalone: true,
  imports: [
    MatTabsModule,
    AsyncPipe,
    CreatePostComponent,
    CreateExerciseComponent,
  ],
  templateUrl: './create-content.component.html',
  styleUrl: './create-content.component.scss',
})
export class CreateContentComponent implements OnInit {
  equipment$?: Observable<EquipmentResource[]>;
  muscleGroups$?: Observable<MuscleGroupDetailsResource[]>;
  variations$?: Observable<ExerciseResource[]>;

  categories$?: Observable<CategoryViewResource[]>;
  tags$?: Observable<TagViewResource[]>;

  constructor(
    private readonly equipmentService: EquipmentService,
    private readonly muscleGroupService: MuscleGroupService,
    private readonly exerciseService: ExerciseService,
    private readonly categoryService: CategoryService,
    private readonly tagService: TagService,
    private readonly postService: PostService
  ) {}

  ngOnInit(): void {
    this.equipment$ = this.equipmentService.getAll();
    this.muscleGroups$ = this.muscleGroupService.getAll();

    this.categories$ = this.categoryService.getCategories();
    this.tags$ = this.tagService.getTags();
  }

  onExerciseSearch(search: string) {
    this.variations$ = this.exerciseService.getVariations(search);
  }

  onExerciseSubmit(data: CreateExerciseResource) {
    this.exerciseService.createExercise(data).subscribe();
  }

  onPostSbumit(post: CreatePostResource) {
    this.postService.createPost(post).subscribe();
  }
}
