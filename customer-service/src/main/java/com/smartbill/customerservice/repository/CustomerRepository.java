package com.smartbill.customerservice.repository;

import com.smartbill.customerservice.model.Customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByName(String name);
}
