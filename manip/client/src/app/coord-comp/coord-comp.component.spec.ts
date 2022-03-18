import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoordCompComponent } from './coord-comp.component';

describe('CoordCompComponent', () => {
  let component: CoordCompComponent;
  let fixture: ComponentFixture<CoordCompComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoordCompComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoordCompComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
