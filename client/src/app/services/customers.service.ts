import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions} from '@angular/http';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/map';

@Injectable()
export class CustomersService {

  options;
  domain = 'http://localhost:8080/';

  constructor(
    private http: HttpClient
  ) {
  }


  createHeaders() {
    this.options = new RequestOptions({
      headers: new Headers({
        'Content-Type': 'application/json'
      })
    });
  }

  newCustomer(customer, type, successCallback) {
    return this.callPOST(this.domain + 'types/' + type + '/customers', customer, successCallback);
  }

  getTenCustomers(successCallback) {
    this.callGET(this.domain + 'customers?page=0&size=10&sort=modifiedWhen,desc', successCallback);
  }

  getTypeById(id, successCallback) {
    this.callGET(this.domain + 'types/' + id, successCallback);
  }

  getAllTypes(successCallback) {
    this.callGET(this.domain + 'types/', successCallback);
  }


  public callGET(url: string, successCallback: Function): void {
    this.http.get(url, this.options)
      .subscribe((res: any) => {
        successCallback(res);
      }, (err) => {
        console.log(err);
      });
  }

  public callPOST(url: string, body: any, successCallBack: Function): void {
    this.http.post(url, body)
      .subscribe((res: any) => {
        successCallBack(res);
      }, (err) => {
        console.log(err);
      });
  }
}
