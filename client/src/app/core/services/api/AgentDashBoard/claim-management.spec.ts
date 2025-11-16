import { TestBed } from '@angular/core/testing';

import { ClaimManagement } from './claim-management';

describe('ClaimManagement', () => {
  let service: ClaimManagement;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClaimManagement);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
