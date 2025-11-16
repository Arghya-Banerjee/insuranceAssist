import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientClaimHistory } from './client-claim-history';

describe('ClientClaimHistory', () => {
  let component: ClientClaimHistory;
  let fixture: ComponentFixture<ClientClaimHistory>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClientClaimHistory]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClientClaimHistory);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
