import { TestBed } from '@angular/core/testing';

import { VaccinationCentresService } from './vaccination-centres.service';

describe('VaccinationCentresService', () => {
  let service: VaccinationCentresService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VaccinationCentresService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
