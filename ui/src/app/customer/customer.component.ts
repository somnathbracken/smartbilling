import { Component, OnInit } from '@angular/core';
import { CustomerService } from './customer.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
})
export class CustomerComponent implements OnInit {
  customer = {
    name: '',
    email: '',
    phone: '',
    address: ''
  };

  customers: any[] = [];

  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {
    this.getCustomers(); // load on init
  }

  saveCustomer() {
    this.customerService.saveCustomer(this.customer).subscribe(() => {
      this.getCustomers(); // reload after save
      this.customer = { name: '', email: '', phone: '', address: '' }; // reset form
    });
  }

  searchCustomer() {
    this.getCustomers(); // or add search logic if needed
  }

  getCustomers() {
    this.customerService.getCustomers().subscribe((data) => {
      this.customers = data;
    });
  }
}
