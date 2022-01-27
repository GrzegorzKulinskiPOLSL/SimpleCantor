import { Component, OnInit } from '@angular/core';
import {RateService} from '../../_services/rateService/rate.service';

@Component({
  selector: 'app-history-list',
  templateUrl: './history-list.component.html',
  styleUrls: ['./history-list.component.css']
})
export class HistoryListComponent implements OnInit {
  historyRates: any;
  constructor(private rateService: RateService) { }

  ngOnInit() {
    this.rateService.getExchangeHistory().then(data => this.historyRates = data);
  }

}
