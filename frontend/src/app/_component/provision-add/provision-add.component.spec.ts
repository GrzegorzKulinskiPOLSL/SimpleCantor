import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProvisionAddComponent } from './provision-add.component';

describe('ProvisionAddComponent', () => {
  let component: ProvisionAddComponent;
  let fixture: ComponentFixture<ProvisionAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProvisionAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProvisionAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
