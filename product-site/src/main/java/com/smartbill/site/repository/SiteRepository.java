package com.smartbill.site.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartbill.site.model.Site;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
    List<Site> findByNameContainingIgnoreCase(String name);
}
