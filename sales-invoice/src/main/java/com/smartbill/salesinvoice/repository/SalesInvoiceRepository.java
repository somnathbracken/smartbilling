package com.smartbill.salesinvoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartbill.salesinvoice.model.SalesInvoice;

public interface SalesInvoiceRepository extends JpaRepository<SalesInvoice, Long> {
}
