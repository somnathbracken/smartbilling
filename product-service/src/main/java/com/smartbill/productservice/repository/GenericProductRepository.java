package com.smartbill.productservice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.smartbill.productservice.model.Brand;
import com.smartbill.productservice.model.GenericProduct;

public interface GenericProductRepository extends JpaRepository<GenericProduct, Integer> {
	Optional<Brand> findByName(String name);
}
