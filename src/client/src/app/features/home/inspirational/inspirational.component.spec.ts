import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InspirationalComponent } from './inspirational.component';

describe('InspirationalComponent', () => {
  let component: InspirationalComponent;
  let fixture: ComponentFixture<InspirationalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InspirationalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InspirationalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
