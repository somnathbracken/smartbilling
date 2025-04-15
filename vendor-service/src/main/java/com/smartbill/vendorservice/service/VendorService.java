package com.smartbill.vendorservice.service;

import java.util.List;

import com.smartbill.vendorservice.model.Vendor;

public interface VendorService {
    List<Vendor> getAll();
    Vendor getById(Long id);
    Vendor save(Vendor customer);
    void delete(Long id);
	Vendor getByName(String name);
}
