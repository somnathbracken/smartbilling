package com.smartbill.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbill.productservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByNameContainingIgnoreCaseOrProductCodeContainingIgnoreCase(String name, String productCode);
//    List<Product> findByStatus(String status);
    List<Product> findByCategoryId(String categoryId);
}
