import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {

  constructor(private http: HttpClient) {}

  async getCurrency() {
     return this.http.get('http://localhost:8080/currencies/', {observe: 'response'}).toPromise();
  }

  async addCurrency(model: any) {

    return this.http.post('http://localhost:8080/currencies/add', model, { observe: 'response' }).toPromise();
  }


}
