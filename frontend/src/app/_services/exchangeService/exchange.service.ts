import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ExchangeService {

  constructor(private http: HttpClient) { }

  calculation(code: string, model: any) {
    return this.http.post('http://localhost:8080/exchange/' + code, model);
  }
}
