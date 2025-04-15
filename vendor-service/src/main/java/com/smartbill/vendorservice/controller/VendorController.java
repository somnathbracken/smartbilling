package com.smartbill.vendorservice.controller;

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

import com.smartbill.vendorservice.model.Vendor;
import com.smartbill.vendorservice.repository.VendorRepository;

@RestController
@RequestMapping("/api/vendors")
@CrossOrigin(origins = "*")
public class VendorController {
    @Autowired
    private VendorRepository vendorRepository;

    @GetMapping("/listAll")
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @PostMapping
    public Vendor createVendor(@RequestBody Vendor customer) {
        return vendorRepository.save(customer);
    }

    @PutMapping("/{id}")
    public Vendor updateVendor(@PathVariable Long id, @RequestBody Vendor vendor) {
        return vendorRepository.findById(id)
            .map(c -> {
                c.setName(vendor.getName());
                c.setEmail(vendor.getEmail());
                c.setPhone(vendor.getPhone());
                c.setAddress(vendor.getAddress());
                return vendorRepository.save(c);
            }).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteVendor(@PathVariable Long id) {
    	vendorRepository.deleteById(id);
    }

    @GetMapping("/search")
    public List<Vendor> searchVendors(@RequestParam String keyword) {
        return vendorRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrAddressContainingIgnoreCase(keyword, keyword, keyword, keyword);
    }
    
}
