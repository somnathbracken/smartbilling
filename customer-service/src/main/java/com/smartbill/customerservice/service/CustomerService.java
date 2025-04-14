package com.smartbill.customerservice.service;

import com.smartbill.customerservice.model.Customer;
import java.util.List;

public interface CustomerService {
    List<Customer> getAll();
    Customer getById(Long id);
    Customer save(Customer customer);
    void delete(Long id);
	Customer getByName(String name);
}
