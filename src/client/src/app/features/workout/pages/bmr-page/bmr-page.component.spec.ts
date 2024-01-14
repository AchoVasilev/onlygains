import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BmrPageComponent } from './bmr-page.component';

describe('BmrPageComponent', () => {
  let component: BmrPageComponent;
  let fixture: ComponentFixture<BmrPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [BmrPageComponent],
    });
    fixture = TestBed.createComponent(BmrPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
