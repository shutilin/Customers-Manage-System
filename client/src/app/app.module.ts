import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModel} from './app-routing.module';
import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {HomeComponent} from './components/home/home.component';
import {CustomersComponent} from './components/customers/customers.component';
import {CustomersService} from './services/customers.service';
import {HttpClientService} from './services/httpclient.service';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CustomersComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModel,
    HttpClientModule,
    ReactiveFormsModule
  ],
    providers: [CustomersService, HttpClientService],
  bootstrap: [AppComponent]
})
export class AppModule { }
