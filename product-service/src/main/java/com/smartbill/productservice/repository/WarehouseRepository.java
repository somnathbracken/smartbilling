package com.smartbill.productservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbill.productservice.model.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
	Optional<Warehouse> findByName(String name);
}

