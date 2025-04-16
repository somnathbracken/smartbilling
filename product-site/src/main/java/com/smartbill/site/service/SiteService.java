package com.smartbill.site.service;

import java.util.List;

import com.smartbill.site.model.Site;

public interface SiteService {
    List<Site> getAll();
    Site getById(Long id);
    Site save(Site site);
    void delete(Long id);
    Site getByName(String name);
}
