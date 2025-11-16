import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NetworkHospitals } from './network-hospitals';

describe('NetworkHospitals', () => {
  let component: NetworkHospitals;
  let fixture: ComponentFixture<NetworkHospitals>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NetworkHospitals]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NetworkHospitals);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
