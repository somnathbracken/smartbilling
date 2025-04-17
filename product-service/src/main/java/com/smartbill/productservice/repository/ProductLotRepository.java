package com.smartbill.productservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.smartbill.productservice.model.ProductLot;

public interface ProductLotRepository extends JpaRepository<ProductLot, Integer> {
	List<ProductLot> findByLotCodeContainingIgnoreCase(String lotCode);
}

