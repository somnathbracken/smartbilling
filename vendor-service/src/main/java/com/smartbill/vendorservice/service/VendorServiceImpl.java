package com.smartbill.vendorservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartbill.vendorservice.model.Vendor;
import com.smartbill.vendorservice.repository.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository repository;

    public VendorServiceImpl(VendorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Vendor> getAll() {
        return repository.findAll();
    }

    @Override
    public Vendor getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    
    @Override
    public Vendor save(Vendor customer) {
        return repository.save(customer);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

	@Override
	public Vendor getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
