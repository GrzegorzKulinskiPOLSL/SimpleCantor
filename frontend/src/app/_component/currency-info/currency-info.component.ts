import {Component, OnInit} from '@angular/core';
import {CurrencyService} from '../../_services/currencyService/currency.service';
import {StoreService} from '../../_services/storeService/store.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-currency-info',
  templateUrl: './currency-info.component.html',
  styleUrls: ['./currency-info.component.css']
})
export class CurrencyInfoComponent implements OnInit {
  currency: any;
  currencies: any;
  subscription: Subscription;

  constructor(private currencyService: CurrencyService,
              private storeService: StoreService) {
    this.subscription = this.storeService.dataContent$.subscribe((data) => {
      this.currencies = data;
    });
  }

  refresh = false;

  async ngOnInit() {
    this.refresh = false;
    this.currencies = undefined;

    this.storeService.getData().subscribe(data => this.currencies = data);

    this.refresh = true;
  }
}
