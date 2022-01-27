import {Component, OnInit} from '@angular/core';
import {CurrencyService} from '../../_services/currencyService/currency.service';
import {StoreService} from '../../_services/storeService/store.service';

@Component({
  selector: 'app-currency',
  templateUrl: './management.component.html',
  styleUrls: ['./management.component.css']
})
export class ManagementComponent implements OnInit {
  isAuthenticated: boolean;
  loading = false;

  constructor(private currencyService: CurrencyService, private storeService: StoreService) { }

  async ngOnInit() {
    this.isAuthenticated = true; // await this.oktaAuth.isAuthenticated();

    // this.oktaAuth.$authenticationState.subscribe(
    //   (isAuthenticated: boolean) => this.isAuthenticated = isAuthenticated
    // );

    if (this.isAuthenticated) {
        await this.storeService.isInitialized();
        this.loading = true;
    }
  }



}
