import { TestBed, inject } from '@angular/core/testing';

import { TargetServiceService } from './target-service.service';

describe('TargetServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TargetServiceService]
    });
  });

  it('should be created', inject([TargetServiceService], (service: TargetServiceService) => {
    expect(service).toBeTruthy();
  }));
});
