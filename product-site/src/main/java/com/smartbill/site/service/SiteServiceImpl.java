package com.smartbill.site.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartbill.site.model.Site;
import com.smartbill.site.repository.SiteRepository;

@Service
public class SiteServiceImpl implements SiteService {

    private final SiteRepository repository;

    public SiteServiceImpl(SiteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Site> getAll() {
        return repository.findAll();
    }

    @Override
    public Site getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    
    @Override
    public Site save(Site site) {
        return repository.save(site);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

	@Override
	public Site getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
