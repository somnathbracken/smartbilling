package com.smartbill.salesinvoice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbill.salesinvoice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByNameContainingIgnoreCase(String name);
//    List<Product> findByStatus(String status);
    List<Product> findByCategoryId(String categoryId);
}
