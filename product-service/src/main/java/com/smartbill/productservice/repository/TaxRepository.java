package com.smartbill.productservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbill.productservice.model.Tax;

public interface TaxRepository extends JpaRepository<Tax, Integer> {
	Optional<Tax> findByName(String name);
}
