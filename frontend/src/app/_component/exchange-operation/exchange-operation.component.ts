import {Component, OnInit} from '@angular/core';
import {CurrentRate} from '../../_models/currentRate';
import {RateService} from '../../_services/rateService/rate.service';
import {ExchangeRequest} from '../../_models/exchangeRequest';
import {ExchangeService} from '../../_services/exchangeService/exchange.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-exchange-operation',
  templateUrl: './exchange-operation.component.html',
  styleUrls: ['./exchange-operation.component.css']
})

export class ExchangeOperationComponent implements OnInit {
  currencies: any;
  currency: CurrentRate;
  result: any;
  // model = new ExchangeRequest(0.0, '');
  submitted = false;
  exchange: FormGroup;

  constructor(private rateService: RateService, private exchangeService: ExchangeService, private formBuilder: FormBuilder) {
  }

  get f() { return this.exchange.controls; }

  onSubmit() {
    this.submitted = true;
    if (this.exchange.invalid) {
      return;
    }
    this.exchangeService.calculation(this.currency.currencyCode, this.exchange.value).subscribe(data => this.result = data);

    this.exchange.reset();
    this.submitted = false;
  }

  ngOnInit() {
    this.exchange = this.formBuilder.group({
      amount: ['', [Validators.required, Validators.min(1)]],
      operationType: ['', [Validators.required]]
    });

    this.rateService.getAllCurrentRates().then(data => this.currencies = data);
  }

}
