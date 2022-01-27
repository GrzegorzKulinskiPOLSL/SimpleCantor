import {Component, OnInit} from '@angular/core';
import {ProvisionService} from '../../_services/provisionService/provision.service';
import {CurrencyService} from '../../_services/currencyService/currency.service';
import {CurrencyModel} from '../../_models/currencyModel';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {StoreService} from '../../_services/storeService/store.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-provision-add',
  templateUrl: './provision-add.component.html',
  styleUrls: ['./provision-add.component.css']
})
export class ProvisionAddComponent implements OnInit {
  currencies: Array<any>;
  currency: any;
  all = new CurrencyModel('ALL', 'ALL');
  subscription: Subscription;
  submitted = false;
  addProvision: FormGroup;
  info: any;

  get f() {
    return this.addProvision.controls;
  }

  constructor(private provisionService: ProvisionService,
              private currencyService: CurrencyService,
              private formBuilder: FormBuilder,
              private storeService: StoreService) {
    this.subscription = this.storeService.dataContent$.subscribe((data) => {
      this.currencies = data;
      this.currency = undefined;
      this.ngOnInit();
  }); }

  async onSubmit() {
    this.submitted = true;

    if (this.addProvision.invalid) {
      return;
    }

    await this.provisionService.addRate(this.currency.currencyCode, this.addProvision.value).then(data => {
        if ( data.status === 202) {
          this.info = 'Dodano ';
        } else {
          this.info = 'Błąd ';
        }
        this.info += 'Http status: ' + data.status + ' ' + data.statusText;
      });
    await this.provisionService.refresh();
    this.addProvision.reset();
    this.submitted = false;
    this.ngOnInit();
  }

  async ngOnInit() {
    this.addProvision = this.formBuilder.group({
      bid: ['', [Validators.min(0.0001)]],
      ask: ['', [Validators.min(0.0001)]]
    }, { validators: checkValue });
    this.storeService.getData().subscribe(data => this.currencies = data);
  }
}

export function checkValue(c: AbstractControl) {
  if (!c.get('bid').value && !c.get('ask').value) {
    return {error: true};
  } else {
    return null;
  }

}
