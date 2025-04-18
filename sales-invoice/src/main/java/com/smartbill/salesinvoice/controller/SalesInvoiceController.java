package com.smartbill.salesinvoice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartbill.salesinvoice.model.SalesInvoice;
import com.smartbill.salesinvoice.service.SalesInvoiceService;

@RestController
@RequestMapping("/api/salesinvoices")
@CrossOrigin(origins = "*")
public class SalesInvoiceController {

    @Autowired
    private SalesInvoiceService invoiceService;

    @GetMapping
    public List<SalesInvoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/{id}")
    public Optional<SalesInvoice> getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id);
    }

    @PostMapping
    public SalesInvoice saveInvoice(@RequestBody SalesInvoice invoice) {
        return invoiceService.saveInvoice(invoice);
    }

    @DeleteMapping("/{id}")
    public void deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
    }
}
