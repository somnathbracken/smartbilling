package com.smartbill.site.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartbill.site.model.Site;
import com.smartbill.site.repository.SiteRepository;

@RestController
@RequestMapping("/api/sites")
@CrossOrigin(origins = "*")
public class SiteController {
    @Autowired
    private SiteRepository siteRepository;

    @GetMapping("/listAll")
    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }

    @PostMapping
    public Site createSite(@RequestBody Site site) {
        return siteRepository.save(site);
    }

    @PutMapping("/{id}")
    public Site updateSite(@PathVariable Long id, @RequestBody Site site) {
        return siteRepository.findById(id)
            .map(c -> {
                c.setName(site.getName());
                return siteRepository.save(c);
            }).orElseThrow(() -> new RuntimeException("Site not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteSite(@PathVariable Long id) {
    	siteRepository.deleteById(id);
    }

    @GetMapping("/search")
    public List<Site> searchSites(@RequestParam String keyword) {
        return siteRepository.findByNameContainingIgnoreCase(keyword);
    }
    
}
