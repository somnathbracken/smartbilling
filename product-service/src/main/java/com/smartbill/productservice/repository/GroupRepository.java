package com.smartbill.productservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbill.productservice.model.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {
	Optional<Group> findByName(String name);
}
