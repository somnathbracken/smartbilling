package com.smartbill.customerservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartbill.customerservice.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrAddressContainingIgnoreCase(String name, String email, String phone, String address);
}
