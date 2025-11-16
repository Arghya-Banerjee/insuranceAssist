import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientRegisterDependents } from './client-register-dependents';

describe('ClientRegisterDependents', () => {
  let component: ClientRegisterDependents;
  let fixture: ComponentFixture<ClientRegisterDependents>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClientRegisterDependents]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClientRegisterDependents);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
