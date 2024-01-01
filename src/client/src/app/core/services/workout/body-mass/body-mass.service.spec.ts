import { TestBed } from '@angular/core/testing';

import { BodyMassService } from './body-mass.service';

describe('BodyMassService', () => {
  let service: BodyMassService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BodyMassService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
