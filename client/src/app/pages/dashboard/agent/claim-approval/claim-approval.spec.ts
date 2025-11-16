import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClaimApproval } from './claim-approval';

describe('ClaimApproval', () => {
  let component: ClaimApproval;
  let fixture: ComponentFixture<ClaimApproval>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClaimApproval]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClaimApproval);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
