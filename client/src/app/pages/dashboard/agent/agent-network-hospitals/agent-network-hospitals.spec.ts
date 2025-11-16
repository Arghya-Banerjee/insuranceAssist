import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgentNetworkHospitals } from './agent-network-hospitals';

describe('AgentNetworkHospitals', () => {
  let component: AgentNetworkHospitals;
  let fixture: ComponentFixture<AgentNetworkHospitals>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AgentNetworkHospitals]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AgentNetworkHospitals);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
