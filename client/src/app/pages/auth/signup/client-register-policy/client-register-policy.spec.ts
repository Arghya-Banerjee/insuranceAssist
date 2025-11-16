import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientRegisterPolicy } from './client-register-policy';

describe('ClientRegisterPolicy', () => {
  let component: ClientRegisterPolicy;
  let fixture: ComponentFixture<ClientRegisterPolicy>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClientRegisterPolicy]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClientRegisterPolicy);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

