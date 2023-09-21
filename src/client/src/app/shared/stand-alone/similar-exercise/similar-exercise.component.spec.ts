import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SimilarExerciseComponent } from './similar-exercise.component';

describe('SimilarExerciseComponent', () => {
  let component: SimilarExerciseComponent;
  let fixture: ComponentFixture<SimilarExerciseComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [SimilarExerciseComponent]
    });
    fixture = TestBed.createComponent(SimilarExerciseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
