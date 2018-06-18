import {Component, OnInit} from '@angular/core';
import {CustomersService} from '../../services/customers.service';
import {FormControl, FormGroup, FormBuilder, Validators} from '@angular/forms';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {

  customers: Array<Customer>;
  currentCustomer: Customer;
  isEdit = false;
  currentId: number;
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
    this.newForm = !this.newForm;
  }

  createNewCustomerForm() {
    this.form = new FormGroup({
      firstName: new FormControl('', Validators.compose([
        Validators.required,
        Validators.maxLength(50),
        Validators.pattern('^[a-zA-Z]+$')
      ])),
      lastName: new FormControl('', Validators.compose([
        Validators.required,
        Validators.maxLength(50),
        Validators.pattern('^[a-zA-Z]+$')
      ])),
      title: new FormControl('', Validators.compose([
        Validators.required,
        Validators.maxLength(3),
        Validators.pattern('^[a-zA-Z]+$')
      ])),
      type: new FormControl('', Validators.required)
    });
  }

  newCustomer(customer, type) {
    this.customersService.newCustomer(customer, type, res => {
      this.newForm = false;
      this.getTenCustomers();
    });
  }

  putCustomer(customer, type, id) {
    console.log(customer);
    console.log(id);
  }

  getTenCustomers() {
    this.customersService.getTenCustomers(res => {
      this.customers = res.content;
      for (const customer of this.customers) {
        this.getTypeById(customer.custTypeId, res => {
          customer.type = res.content[0].caption;
        });
      }
    });
  }

  getAlltypes() {
    this.customersService.getAllTypes(res => {
      this.types = res.content;
    });
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
    const typeSend = {typeId: this.form.get('type').value};

    if (!this.isEdit) {
      this.newCustomer(customerSend, typeSend.typeId);
      this.form.reset();
    } else {
      this.putCustomer(customerSend, typeSend.typeId, this.currentId);
      this.isEdit = false;
      this.form.reset();
    }
  }

  editCustomer(customer) {
    this.isEdit = true;
    this.newForm = true;
    this.form.controls['firstName'].setValue(customer.firstName);
    this.form.controls['lastName'].setValue(customer.lastName);
    this.form.controls['title'].setValue(customer.title);
    this.form.controls['type'].setValue(customer.custTypeId);
    this.currentId = customer.id;
  }

  deleteCustomer(id) {
    this.customersService.deleteCustomer(id, res => {
      this.getTenCustomers();
    });
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
  type?: string;
}
