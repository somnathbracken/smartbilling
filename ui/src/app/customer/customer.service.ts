import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  private baseUrl = 'http://localhost:8080/api/customers'; // Adjust if different

  constructor(private http: HttpClient) {}

  // ✅ GET all customers
  getCustomers(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl+"/listAll");
  }

  // ✅ POST a new customer
  saveCustomer(customer: any): Observable<any> {
    return this.http.post(this.baseUrl, customer);
  }

  // You can also add delete, update etc. later as needed.
}
