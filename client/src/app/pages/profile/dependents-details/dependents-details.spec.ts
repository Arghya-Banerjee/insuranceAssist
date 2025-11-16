import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DependentsDetails } from './dependents-details';

describe('DependentsDetails', () => {
  let component: DependentsDetails;
  let fixture: ComponentFixture<DependentsDetails>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DependentsDetails]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DependentsDetails);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
