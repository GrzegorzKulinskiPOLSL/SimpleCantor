import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExchangeOperationComponent } from './exchange-operation.component';

describe('ExchangeOperationComponent', () => {
  let component: ExchangeOperationComponent;
  let fixture: ComponentFixture<ExchangeOperationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExchangeOperationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExchangeOperationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
