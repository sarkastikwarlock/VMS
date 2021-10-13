import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VaccinationCentresComponent } from './vaccination-centres.component';

describe('VaccinationCentresComponent', () => {
  let component: VaccinationCentresComponent;
  let fixture: ComponentFixture<VaccinationCentresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VaccinationCentresComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VaccinationCentresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
