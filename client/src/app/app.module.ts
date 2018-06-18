import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModel } from './app-routing.module';
import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { ToolbarComponent } from './components/toolbar/toolbar.component';
import { HomeComponent } from './components/home/home.component';
import { CustomersComponent } from './components/customers/customers.component';
import { CustomersService } from './services/customers.service';
import { TypesService } from './services/types.service';
import { NewCustomerComponent } from './components/customers/new-customer/new-customer.component';


@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    HomeComponent,
    CustomersComponent,
    NewCustomerComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModel,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [CustomersService, TypesService],
  bootstrap: [AppComponent]
})
export class AppModule { }
