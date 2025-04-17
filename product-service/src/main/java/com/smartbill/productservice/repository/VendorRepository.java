package com.smartbill.productservice.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbill.productservice.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
	Optional<Vendor> findByName(String name);
}

