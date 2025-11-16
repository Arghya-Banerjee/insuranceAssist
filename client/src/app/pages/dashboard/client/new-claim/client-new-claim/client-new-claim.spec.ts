import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientNewClaim } from './client-new-claim';

describe('ClientNewClaim', () => {
  let component: ClientNewClaim;
  let fixture: ComponentFixture<ClientNewClaim>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClientNewClaim]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClientNewClaim);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
