import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TargetViewPrimeComponent } from './target-view-prime.component';

describe('TargetViewPrimeComponent', () => {
  let component: TargetViewPrimeComponent;
  let fixture: ComponentFixture<TargetViewPrimeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TargetViewPrimeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TargetViewPrimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
