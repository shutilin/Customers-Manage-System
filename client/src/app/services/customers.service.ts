import {Injectable} from '@angular/core';
import 'rxjs/add/operator/map';
import {HttpClientService} from './httpclient.service';

@Injectable()
export class CustomersService {

    domain = 'http://localhost:8080/';

    constructor(
        private http: HttpClientService
    ) {
    }


    newCustomer(customer, type, successCallback) {
        return this.http.callPOST(this.domain + 'types/' + type + '/customers', customer, successCallback);
    }


    getTenCustomers(successCallback) {
        this.http.callGET(this.domain + 'customers', successCallback, {page: 0, size: 10, sort: 'modifiedWhen,desc'});
    }

    getTypeById(id, successCallback) {
        this.http.callGET(this.domain + 'types/' + id, successCallback);
    }

    getAllTypes(successCallback) {
        this.http.callGET(this.domain + 'types/', successCallback);
    }

    updateCustomer(customer, typeId, customerId, successCallback) {
        this.http.callPUT(this.domain + 'types/' + typeId + '/customers/' + customerId, customer, successCallback);
    }

    deleteCustomer(id, successCallback) {
        this.http.callDELETE(this.domain + 'customers/' + id, successCallback);
    }

    searchCustomers(searchParams, successCallback) {
        let params = {
            firstName: searchParams.firstName,
            lastName: searchParams.lastName,
            page: 0,
            size: 1,
            sort: 'modifiedWhen,desc'
        };
        this.http.callGET(this.domain + 'search/', successCallback, params);
    }

    getSearchPage(searchParams, successCallback) {
        let params = {
            firstName: searchParams.firstName,
            lastName: searchParams.lastName,
            page: searchParams.page,
            size: 1,
            sort: 'modifiedWhen,desc'
        };
        this.http.callGET(this.domain + 'search/', successCallback, params);
    }


}
