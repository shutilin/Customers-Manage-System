import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/map';

@Injectable()
export class CustomersService {

  domain = 'http://localhost:8080/';

  constructor(
    private http: HttpClient
  ) {
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

  updateCustomer(customer, typeId, customerId, successCallback) {
    this.callPUT(this.domain + 'types/' + typeId + '/customers/' + customerId, customer, successCallback);
  }

  deleteCustomer(id, successCallback) {
    this.callDELETE(this.domain + 'customers/' + id, successCallback);
  }

  searchCustomers(searchParams, successCallback) {
    this.callGET(this.domain + 'search/?firstName=' + searchParams.firstName + '&lastName=' + searchParams.lastName, successCallback);
  }


  public callGET(url: string, successCallback: Function): void {
    this.http.get(url)
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

  public callDELETE(url: string, successCallback: Function): void {
    this.http.delete(url)
      .subscribe((res: any) => {
        successCallback(res);
      }, (err) => {
        console.log(err);
      });
  }

  public callPUT(url: string, body: any, successCallback: Function): void {
    this.http.put(url, body)
      .subscribe((res: any) => {
        successCallback(res);
      }, (err) => {
        console.log(err);
      });
  }
}
