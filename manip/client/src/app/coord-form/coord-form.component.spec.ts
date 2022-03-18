import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoordFormComponent } from './coord-form.component';

describe('CoordFormComponent', () => {
  let component: CoordFormComponent;
  let fixture: ComponentFixture<CoordFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoordFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoordFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
