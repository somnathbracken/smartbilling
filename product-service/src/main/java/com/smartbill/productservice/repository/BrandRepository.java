package com.smartbill.productservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbill.productservice.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
	Optional<Brand> findByName(String name);
}
