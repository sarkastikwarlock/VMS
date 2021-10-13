import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookVaccineSlotComponent } from './book-vaccine-slot.component';

describe('BookVaccineSlotComponent', () => {
  let component: BookVaccineSlotComponent;
  let fixture: ComponentFixture<BookVaccineSlotComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookVaccineSlotComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookVaccineSlotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
