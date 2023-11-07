import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkoutDashboardComponent } from './workout-dashboard.component';

describe('WorkoutDashboardComponent', () => {
  let component: WorkoutDashboardComponent;
  let fixture: ComponentFixture<WorkoutDashboardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [WorkoutDashboardComponent]
    });
    fixture = TestBed.createComponent(WorkoutDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
