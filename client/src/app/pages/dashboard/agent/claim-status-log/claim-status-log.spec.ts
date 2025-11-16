import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClaimStatusLog } from './claim-status-log';

describe('ClaimStatusLog', () => {
  let component: ClaimStatusLog;
  let fixture: ComponentFixture<ClaimStatusLog>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClaimStatusLog]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClaimStatusLog);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
