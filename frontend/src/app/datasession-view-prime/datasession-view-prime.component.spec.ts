import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DatasessionViewPrimeComponent } from './datasession-view-prime.component';

describe('DatasessionViewPrimeComponent', () => {
  let component: DatasessionViewPrimeComponent;
  let fixture: ComponentFixture<DatasessionViewPrimeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DatasessionViewPrimeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DatasessionViewPrimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
