import { TestBed, inject } from '@angular/core/testing';

import { DatasessionsService } from './datasessions.service';

describe('DatasessionsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DatasessionsService]
    });
  });

  it('should be created', inject([DatasessionsService], (service: DatasessionsService) => {
    expect(service).toBeTruthy();
  }));
});
