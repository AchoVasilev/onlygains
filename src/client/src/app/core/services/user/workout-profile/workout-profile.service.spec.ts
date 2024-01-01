import { TestBed } from '@angular/core/testing';

import { WorkoutProfileService } from './workout-profile.service';

describe('WorkoutProfileService', () => {
  let service: WorkoutProfileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorkoutProfileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
