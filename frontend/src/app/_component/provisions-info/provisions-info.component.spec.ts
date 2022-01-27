import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProvisionsInfoComponent } from './provisions-info.component';

describe('ProvisionsInfoComponent', () => {
  let component: ProvisionsInfoComponent;
  let fixture: ComponentFixture<ProvisionsInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProvisionsInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProvisionsInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
