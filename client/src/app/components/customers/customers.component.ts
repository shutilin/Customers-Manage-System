import {Component, OnInit} from '@angular/core';
import {CustomersService} from '../../services/customers.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {

  customers: Array<Customer>;
  isEdit = false;
  currentId: number;
  types: Array<any>;
  customerForm: FormGroup;
  searchForm: FormGroup;
  totalPages: number;
  isCustomerForm: boolean = false;
  isSearchForm: boolean = false;
  fakePageArray: Array<number>;
  searchParams;
  pageNumber: number = 0;

  constructor(
    private formBuilder: FormBuilder,
    private customersService: CustomersService
  ) {
    this.createNewCustomerForm();
    this.createSearchForm();
  }

  toggleCustomerForm() {
    this.customerForm.reset();
    if (this.isSearchForm) {
      this.isSearchForm = false;
    }
    this.isCustomerForm = !this.isCustomerForm;
  }

  toggleSearchForm() {
    this.searchForm.reset();
    this.totalPages = 0;
    this.isBlank();
    if (this.isCustomerForm) {
      this.isCustomerForm = false;
    }
    this.isSearchForm = !this.isSearchForm;
  }

  isBlank() {
    if (!this.searchForm.valid) {
      if (!this.searchForm.getRawValue().firstName && !this.searchForm.getRawValue().lastName) {
        this.getTenCustomers();
      }
    }
  }

  createNewCustomerForm() {
    this.customerForm = new FormGroup({
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

  createSearchForm() {
    this.searchForm = new FormGroup({
      firstName: new FormControl('', Validators.compose([
        Validators.required,
        Validators.maxLength(50),
        Validators.pattern('^[a-zA-Z]+$')
      ])),
      lastName: new FormControl('', Validators.compose([
        Validators.required,
        Validators.maxLength(50),
        Validators.pattern('^[a-zA-Z]+$')
      ]))
    });
  }

  newCustomer(customer, type) {
    this.customersService.newCustomer(customer, type, res => {
      this.isCustomerForm = false;
      this.getTenCustomers();
    });
  }

  putCustomer(customer, type, id) {
    this.customersService.updateCustomer(customer, type, id, res => {
      this.isCustomerForm = false;
      this.getTenCustomers();
    });
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
      firstName: this.customerForm.get('firstName').value,
      lastName: this.customerForm.get('lastName').value,
      title: this.customerForm.get('title').value
    };
    const typeSend = {typeId: this.customerForm.get('type').value};

    if (!this.isEdit) {
      this.newCustomer(customerSend, typeSend.typeId);
      this.customerForm.reset();
    } else {
      this.putCustomer(customerSend, typeSend.typeId, this.currentId);
      this.isEdit = false;
      this.customerForm.reset();
    }
  }

  onSearchSubmit() {
    this.searchParams = {
      firstName: this.searchForm.get('firstName').value,
      lastName: this.searchForm.get('lastName').value,
    };
    this.searchCustomers(this.searchParams);
  }

  onSearchPage(pageNumber) {
      this.pageNumber = pageNumber;
      const pageParams = {
          firstName: this.searchParams.firstName,
          lastName: this.searchParams.lastName,
          page: pageNumber
      };
      this.getSearchPage(pageParams);
  }

  getSearchPage(pageParams) {
    this.customersService.getSearchPage(pageParams,res=> {
        this.customers = res.content;
        for (const customer of this.customers) {
            this.getTypeById(customer.custTypeId, res => {
                customer.type = res.content[0].caption;
            });
        }
    })
  }

  searchCustomers(searchParams) {
    this.customersService.searchCustomers(searchParams, res => {
      this.customers = res.content;
      this.fakePageArray = new Array<number>(res.totalPages);
      this.totalPages = res.totalPages;
      for (const customer of this.customers) {
        this.getTypeById(customer.custTypeId, res => {
          customer.type = res.content[0].caption;
        });
      }
    });
  }

  editCustomer(customer) {
    if (this.isSearchForm) {
      this.isSearchForm = false;
    }
    this.isEdit = true;
    this.isCustomerForm = true;
    this.customerForm.controls['firstName'].setValue(customer.firstName);
    this.customerForm.controls['lastName'].setValue(customer.lastName);
    this.customerForm.controls['title'].setValue(customer.title);
    this.customerForm.controls['type'].setValue(customer.custTypeId);
    this.currentId = customer.id;
  }

  deleteCustomer(id) {
    if (confirm('Delete customer with id ' + id)) {
      this.customersService.deleteCustomer(id, res => {
        this.getTenCustomers();
      });
    }
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
