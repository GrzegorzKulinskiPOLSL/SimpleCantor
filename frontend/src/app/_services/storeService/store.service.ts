import {Injectable} from '@angular/core';
import {CurrencyService} from '../currencyService/currency.service';
import {Observable, Subject} from 'rxjs';
import 'rxjs-compat/add/observable/of';


@Injectable({
  providedIn: 'root'
})
export class StoreService {

  private cacheData: any;
  private dataSource = new Subject<any>();

  dataContent$ = this.dataSource.asObservable();

  shareData(data: any): void {
    this.dataSource.next(data);
  }

  constructor(private currencyService: CurrencyService) {
  }


  async isInitialized() {
    if (this.cacheData === undefined) {
      await this.currencyService.getCurrency().then(data => {
        this.cacheData = data.body;
        if (data.status !== 200) {
        }
      });
    }
  }

  getData() {

    if (this.cacheData !== undefined && this.cacheData !== null) {
      return Observable.of(this.cacheData);
    } else {
      this.currencyService.getCurrency().then(data => {
        this.cacheData = data.body;
        return Observable.of(data);
      });
    }
  }

  async refresh() {
    await this.currencyService.getCurrency().then(data => {
      this.cacheData = undefined;
      this.cacheData = data.body;
      this.shareData(data);
    });
  }

}
