package com.smartbill.customerservice.service;

import com.smartbill.customerservice.model.Customer;
import com.smartbill.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Customer> getAll() {
        return repository.findAll();
    }

    @Override
    public Customer getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Customer getByName(String name) {
        return repository.findByName(name).orElse(null);
    }
    
    @Override
    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
