package com.smartbill.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_code")
    private String productCode;

    private String hsnCode;
    
    private String hsnDescription;

	private Integer categoryId;

    private Integer groupId;

    private Integer brandId;

    private Integer genericId;

    private Integer modelId;

    private String name;

    private String description;

    @Column(name = "unit_price")
    private String unitPrice;

    private String hasInstances;

    private String hasLots;

    private String hasAttributes;

    private String defaultUom;

    private String packSize;

    private Double averageCost;

    private String singleUnitProductCode;

    private String dimensionGroup;

    @Column(columnDefinition = "TEXT")
    private String lotInformation;

    @Column(columnDefinition = "TEXT")
    private String warrantyTerms;

    private Boolean isActive = true;

    private Boolean deleted = false;

    private String sku;

    private String gstPercentage;

    private String unitOfMeasure;

    private String vendorId;

    private String siteId;

    private String warehouseId;

    private String stockId;

    private String uomId;

    private String productLotId;

    private Integer quantity;

    private String status;
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getGenericId() {
		return genericId;
	}

	public void setGenericId(Integer genericId) {
		this.genericId = genericId;
	}

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getHasInstances() {
		return hasInstances;
	}

	public void setHasInstances(String hasInstances) {
		this.hasInstances = hasInstances;
	}

	public String getHasLots() {
		return hasLots;
	}

	public void setHasLots(String hasLots) {
		this.hasLots = hasLots;
	}

	public String getHasAttributes() {
		return hasAttributes;
	}

	public void setHasAttributes(String hasAttributes) {
		this.hasAttributes = hasAttributes;
	}

	public String getDefaultUom() {
		return defaultUom;
	}

	public void setDefaultUom(String defaultUom) {
		this.defaultUom = defaultUom;
	}

	public String getPackSize() {
		return packSize;
	}

	public void setPackSize(String packSize) {
		this.packSize = packSize;
	}

	public Double getAverageCost() {
		return averageCost;
	}

	public void setAverageCost(Double averageCost) {
		this.averageCost = averageCost;
	}

	public String getSingleUnitProductCode() {
		return singleUnitProductCode;
	}

	public void setSingleUnitProductCode(String singleUnitProductCode) {
		this.singleUnitProductCode = singleUnitProductCode;
	}

	public String getDimensionGroup() {
		return dimensionGroup;
	}

	public void setDimensionGroup(String dimensionGroup) {
		this.dimensionGroup = dimensionGroup;
	}

	public String getLotInformation() {
		return lotInformation;
	}

	public void setLotInformation(String lotInformation) {
		this.lotInformation = lotInformation;
	}

	public String getWarrantyTerms() {
		return warrantyTerms;
	}

	public void setWarrantyTerms(String warrantyTerms) {
		this.warrantyTerms = warrantyTerms;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getGstPercentage() {
		return gstPercentage;
	}

	public void setGstPercentage(String gstPercentage) {
		this.gstPercentage = gstPercentage;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getUomId() {
		return uomId;
	}

	public void setUomId(String uomId) {
		this.uomId = uomId;
	}

	public String getProductLotId() {
		return productLotId;
	}

	public void setProductLotId(String productLotId) {
		this.productLotId = productLotId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    public String getHsnDescription() {
		return hsnDescription;
	}

	public void setHsnDescription(String hsnDescription) {
		this.hsnDescription = hsnDescription;
	}
}
