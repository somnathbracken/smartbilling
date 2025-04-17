package com.smartbill.productservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbill.productservice.model.Uom;

public interface UomRepository extends JpaRepository<Uom, Integer> {
	Optional<Uom> findByName(String name);
}