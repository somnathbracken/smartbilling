package com.smartbill.salesinvoice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartbill.salesinvoice.model.SalesInvoice;
import com.smartbill.salesinvoice.repository.SalesInvoiceRepository;

@Service
public class SalesInvoiceService {
    @Autowired
    private SalesInvoiceRepository invoiceRepository;

    public List<SalesInvoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<SalesInvoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    public SalesInvoice saveInvoice(SalesInvoice invoice) {
        // Cascade will take care of saving line items
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}
