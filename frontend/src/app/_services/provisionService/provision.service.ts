import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProvisionService {
  private flag = new Subject<any>();
  cache: any;

  flagContent$ = this.flag.asObservable();

  shareChange(data: any): void {
    this.flag.next(data);
  }

  constructor(private http: HttpClient) {
  }

  async getAllSetProvision() {
    return this.http.get('//localhost:8080/currencies/currenciesParameters').toPromise();
  }

  async addRate(currencyCode: string, model: any) {
    return this.http.post('//localhost:8080/currencies/' + currencyCode + '/setParameters', model, {observe: 'response'}).toPromise();
  }

  async refresh() {
    await this.getAllSetProvision().then(data => this.cache = data);
    await this.shareChange(this.cache);
  }
}
