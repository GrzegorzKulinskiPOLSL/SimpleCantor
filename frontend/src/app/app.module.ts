import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {RateService} from './_services/rateService/rate.service';
import {RateListComponent} from './_component/rate-list/rate-list.component';
import {CustomMaterialModule} from './material.module';
import {HistoryListComponent} from './_component/history-list/history-list.component';
import {routing} from './appRoutes';
import { ExchangeOperationComponent } from './_component/exchange-operation/exchange-operation.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ExchangeService} from './_services/exchangeService/exchange.service';
import {AuthInterceptors} from './_Auth/auth.interceptors';
import { ManagementComponent } from './_component/management/management.component';
import { CurrencyInfoComponent } from './_component/currency-info/currency-info.component';
import { ProvisionsInfoComponent } from './_component/provisions-info/provisions-info.component';
import { ProvisionAddComponent } from './_component/provision-add/provision-add.component';
import { CurrencyAddComponent } from './_component/currency-add/currency-add.component';
import { RateAddComponent } from './_component/rate-add/rate-add.component';
import {ProvisionService} from './_services/provisionService/provision.service';
import {CurrencyService} from './_services/currencyService/currency.service';
import {StoreService} from './_services/storeService/store.service';
import {CommonModule} from '@angular/common';
import {
  MatButtonModule, MatCardModule,
  MatFormFieldModule,
  MatInputModule, MatListModule,
  MatOptionModule, MatProgressSpinnerModule,
  MatRadioModule, MatSelectModule, MatSnackBarModule, MatTableModule,
  MatTabsModule,
  MatToolbarModule
} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {RegisterComponent} from "./_component/register/register.component";
import {LoginComponent} from "./_component/login/login.component";

const config = {
  issuer: 'https://dev-575853.oktapreview.com/oauth2/default',
  redirectUri: 'http://localhost:4200/implicit/callback',
  clientId: '0oajcgb4xjsZhcxo20h7'
};

@NgModule({
  declarations: [
    AppComponent,
    RateListComponent,
    HistoryListComponent,
    ExchangeOperationComponent,
    ManagementComponent,
    CurrencyInfoComponent,
    ProvisionsInfoComponent,
    ProvisionAddComponent,
    CurrencyAddComponent,
    RateAddComponent,
    RegisterComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    CustomMaterialModule,
    routing,
    ReactiveFormsModule,
    FormsModule,
    CommonModule,
    BrowserAnimationsModule,
  ],
  providers: [RateService, ExchangeService, ProvisionService, CurrencyService, StoreService,
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptors, multi: true}],
  bootstrap: [AppComponent]

})
export class AppModule { }
