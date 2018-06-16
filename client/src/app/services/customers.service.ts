import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http'
import 'rxjs/add/operator/map';

@Injectable()
export class CustomersService {

  options;
  domain = "http://localhost:8080/";

  constructor(
    private http: Http
  ) { }

  
  createHeaders() {
    this.options = new RequestOptions({
      headers: new Headers({
        'Content-Type': 'application/json' 
      })
    });
  }

  getTenCustomers() {
  	return this.http.get(this.domain + 'customers', this.options).map(res => res.json());
  }

}
