import {RouterModule, Routes} from '@angular/router';
import {RateListComponent} from './_component/rate-list/rate-list.component';
import {HistoryListComponent} from './_component/history-list/history-list.component';
import {ExchangeOperationComponent} from './_component/exchange-operation/exchange-operation.component';
import {ManagementComponent} from './_component/management/management.component';
import {LoginComponent} from './_component/login/login.component';
import {RegisterComponent} from './_component/register/register.component';

const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'currentRates', component: RateListComponent },
  { path: 'histories', component: HistoryListComponent },
  { path: 'exchanges', component: ExchangeOperationComponent},
  { path: 'management', component: ManagementComponent },
  { path: '**', redirectTo: 'currentRates'}
];

export const routing = RouterModule.forRoot(appRoutes);
