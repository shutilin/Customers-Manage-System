import { Component, OnInit } from '@angular/core';
import { CustomersService } from '../../services/customers.service';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {

  customers: Array<Customer>;
  types;
  form;
  newForm = false;

  constructor(
      private formBuilder: FormBuilder,
  		private customersService: CustomersService
  	) {
    this.createNewCustomerForm();
     }

  newCustomerForm() {
    this.newForm = true;
  }

  createNewCustomerForm() {
    this.form = new FormGroup({
      firstName: new FormControl("", Validators.compose([
        Validators.required,
        Validators.maxLength(50),
        Validators.pattern("^[a-zA-Z]+$")
      ])),
      lastName: new FormControl("", Validators.compose([
        Validators.required,
        Validators.maxLength(50),
        Validators.pattern("^[a-zA-Z]+$")
      ])),
      title: new FormControl("", Validators.compose([
        Validators.required,
        Validators.maxLength(3),
        Validators.pattern("^[a-zA-Z]+$")
      ])),
      type: new FormControl("", Validators.required)
    });
  }

  getTenCustomers() {
    this.customersService.getTenCustomers(res => {
    	this.customers = res.content;
    	for(const customer of this.customers) {
    		this.getTypeById(customer.custTypeId, res => {
    			customer.type = res.content[0].title;
    		});
    	}
    });
  }

  getAlltypes() {
    this.customersService.getAllTypes(res => {
      this.types = res.content;
      console.log(this.types);
    })
  }

  getTypeById(id, successCallback) {
 		this.customersService.getTypeById(id, successCallback);
  }

  onCustomerSubmit() {
    const customerSend = {
      firstName: this.form.get('firstName').value,
      lastName: this.form.get('lastName').value,
      title: this.form.get('title').value
    };
    const typeSend ={ typeId: this.form.get('type').value };
    console.log(customerSend);
    console.log(typeSend);
  }


  ngOnInit() {
  	this.getTenCustomers();
    this.getAlltypes();
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
