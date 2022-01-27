import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RateService {

  constructor(private http: HttpClient) {
  }

  getAllCurrentRates() {
    return this.http.get('//localhost:8080/exchange/rate').toPromise();
  }

  async getExchangeHistory() {
    return this.http.get('//localhost:8080/exchange/history').toPromise();
  }

  async addRate(currencyCode: string, model: any) {
    return this.http.post('//localhost:8080/currencies/' + currencyCode + '/addRate', model, { observe: 'response'}).toPromise();
  }

  async addFromApi() {
    return this.http.post('//localhost:8080/currencies/addAllRateApi', '', {observe: 'response'}).toPromise();
  }
}
