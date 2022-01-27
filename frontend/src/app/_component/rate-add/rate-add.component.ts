import {Component, OnInit} from '@angular/core';
import {CurrencyService} from '../../_services/currencyService/currency.service';
import {RateService} from '../../_services/rateService/rate.service';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {StoreService} from '../../_services/storeService/store.service';
import {Subscription} from 'rxjs';


@Component({
  selector: 'app-rate-add',
  templateUrl: './rate-add.component.html',
  styleUrls: ['./rate-add.component.css']
})

export class RateAddComponent implements OnInit {
  currencies: Array<any>;
  currency: any;
  subscription: Subscription;
  submitted = false;
  addRate: FormGroup;
  refresh = true;
  info: any;

  constructor(private currencyService: CurrencyService,
              private rateService: RateService,
              private formBuilder: FormBuilder,
              private storeService: StoreService) {
    this.subscription = this.storeService.dataContent$.subscribe((data) => {
      this.currencies = data;
      this.refresh = false;
      this.currency = undefined;
      this.ngOnInit();
    });
  }

  get f() {
    return this.addRate.controls;
  }

  get g() {
    return this.addRate.errors;
  }

  ngOnInit() {
    this.storeService.getData().subscribe(data => this.currencies = data);

    this.addRate = this.formBuilder.group({
      currencyPurchase: ['', [Validators.min(0.0001),
        Validators.required]],
      currencySale: ['', [Validators.min(0.0001),
        Validators.required],
      ]
    }, {validators: checkValue});
    this.addRate.reset();
    this.submitted = false;
    this.refresh = true;

  }

  async onSubmit() {
    this.submitted = true;

    if (this.addRate.invalid) {
      return;
    }

    await this.rateService.addRate(this.currency.currencyCode, this.addRate.value).then(data => {
       if ( data.status === 202) {
         this.info = 'Dodano ';
       } else {
         this.info = 'Błąd ';
       }
       this.info += 'Http status: ' + data.status + ' ' + data.statusText;
    });
    await this.storeService.refresh();
    this.addRate.reset();
    this.submitted = false;
  }

  async onSubmitAll() {
    await this.rateService.addFromApi().then();
    await this.storeService.refresh();
  }
}

export function checkValue(c: AbstractControl) {
  if (!c.get('currencyPurchase').value || !c.get('currencySale').value ||
    (c.get('currencySale').value < c.get('currencyPurchase').value)) {
    return {error: true};
  } else {
    return null;
  }

}
