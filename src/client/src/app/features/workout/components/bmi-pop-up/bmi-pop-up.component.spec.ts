import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BmiPopUpComponent } from './bmi-pop-up.component';

describe('BmiPopUpComponent', () => {
  let component: BmiPopUpComponent;
  let fixture: ComponentFixture<BmiPopUpComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [BmiPopUpComponent]
    });
    fixture = TestBed.createComponent(BmiPopUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
