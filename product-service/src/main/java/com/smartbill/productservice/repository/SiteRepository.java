package com.smartbill.productservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbill.productservice.model.Site;

public interface SiteRepository extends JpaRepository<Site, Integer> {
	Optional<Site> findByName(String name);
}

