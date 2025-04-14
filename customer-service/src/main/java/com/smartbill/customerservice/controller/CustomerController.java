package com.smartbill.customerservice.controller;

import com.smartbill.customerservice.model.Customer;
import com.smartbill.customerservice.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Customer> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Customer get(@PathVariable Long id) {
        return service.getById(id);
    }
    
    @GetMapping("/name/{name}")
    public Customer getByName(@PathVariable String name) {
        return service.getByName(name);
    }

    @PostMapping
    public Customer save(@RequestBody Customer customer) {
        return service.save(customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
