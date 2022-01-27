import { Component, OnInit } from '@angular/core';
import {ProvisionService} from '../../_services/provisionService/provision.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-provisions-info',
  templateUrl: './provisions-info.component.html',
  styleUrls: ['./provisions-info.component.css']
})
export class ProvisionsInfoComponent implements OnInit {
  provisions: any;
  displayedColumns: string[] = ['currencyCode', 'bid', 'ask'];
  subscription: Subscription;

  constructor(private provisionService: ProvisionService ) {
    this.subscription = this.provisionService.flagContent$.subscribe(onloadeddata => this.provisions = onloadeddata);
  }

  async ngOnInit() {
    if (this.provisions === undefined ) {
      await this.provisionService.getAllSetProvision().then(data => this.provisions = data );
    }
  }





}
