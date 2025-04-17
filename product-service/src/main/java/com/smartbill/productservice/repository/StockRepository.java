package com.smartbill.productservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbill.productservice.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {
	Optional<Stock> findByName(String name);
}

