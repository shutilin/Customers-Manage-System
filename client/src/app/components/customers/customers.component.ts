import { Component, OnInit } from '@angular/core';
import { CustomersService } from '../../services/customers.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {

  customers: Array<Customer>;
  types = [];		

  constructor(
  		private customersService: CustomersService
  	) { }

  getTenCustomers() {
    this.customersService.getTenCustomers(res => {
    	this.customers = res.content;
    	for(let customer of this.customers) {
    		this.getTypeById(customer.custTypeId, res => {
    			customer.type = res.content[0].title;
    		});
    	}
    });
  }

  getTypeById(id, successCallback) {
 		this.customersService.getTypeById(id, successCallback);
  }

  ngOnInit() {
  	this.getTenCustomers();

  }

}

export interface Customer {
	id: number; 
	title: string;
	firstName: string;
	lastName: string; 
	modifiedWhen: string;
	custTypeId: number;
	type?:string;
}