import { Component, OnInit } from '@angular/core';
import { CustomersService } from '../../services/customers.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {

  customers;		

  constructor(
  		private customersService: CustomersService
  	) { }

  getTenCustomers() {
    this.customersService.getTenCustomers().subscribe(data => {
      this.customers = data.content;
      console.log(this.customers);
    })
  }

  ngOnInit() {
  	this.getTenCustomers();
  }

}
