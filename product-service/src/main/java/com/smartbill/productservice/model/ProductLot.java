package com.smartbill.productservice.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_lot")
public class ProductLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lot_code")
    private String lotCode;

    @Column(name = "date_manufactured")
    private LocalDate dateManufactured;

    @Column(name = "date_expiry")
    private LocalDate dateExpiry;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLotCode() {
		return lotCode;
	}

	public void setLotCode(String lotCode) {
		this.lotCode = lotCode;
	}

	public LocalDate getDateManufactured() {
		return dateManufactured;
	}

	public void setDateManufactured(LocalDate dateManufactured) {
		this.dateManufactured = dateManufactured;
	}

	public LocalDate getDateExpiry() {
		return dateExpiry;
	}

	public void setDateExpiry(LocalDate dateExpiry) {
		this.dateExpiry = dateExpiry;
	}
}
