import {Component, OnInit} from '@angular/core';
import {RateService} from '../../_services/rateService/rate.service';

@Component({
  selector: 'app-rate-list',
  templateUrl: './rate-list.component.html',
  styleUrls: ['./rate-list.component.css']
})
export class RateListComponent implements OnInit {
  rates: any;

  constructor(private rateService: RateService) {
  }


  ngOnInit() {
    this.rateService.getAllCurrentRates().then(data =>
      this.rates = data);
  }

}
