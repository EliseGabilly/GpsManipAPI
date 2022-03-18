import { TestBed } from '@angular/core/testing';

import { CoordinatesServiceService } from './coordinates-service.service';

describe('CoordinatesServiceService', () => {
  let service: CoordinatesServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CoordinatesServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
