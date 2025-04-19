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

    //@Column(name = "product_code")
    private String productCode;
    
    private String barCode;
    
    private String hsnCode;
    
    private String hsnDescription;

	private String categoryId;

    private String groupId;

    private String brandId;

    private String genericId;

    private String modelId;

	private String name;

    private String description;

    private String pruchasePrice;
    
    private String mrp;

    private String hasInstances;

    private String hasLots;

    private String hasAttributes;

//    private String defaultUom;

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

//    private String uomId;

    private String productLotId;
    
    private String discounts;
    
    private Integer quantity;

//    private String status;
    
    private String purchaseOrderId;
    
    private String productAttributeId;
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getDiscounts() {
		return discounts;
	}

	public void setDiscounts(String discounts) {
		this.discounts = discounts;
	}

	public String getPruchasePrice() {
		return pruchasePrice;
	}

	public void setPruchasePrice(String pruchasePrice) {
		this.pruchasePrice = pruchasePrice;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
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

    public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public void setGenericId(String genericId) {
		this.genericId = genericId;
	}

	public void setModelId(String modelId) {
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
		return pruchasePrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.pruchasePrice = unitPrice;
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

    public String getHsnDescription() {
		return hsnDescription;
	}

	public void setHsnDescription(String hsnDescription) {
		this.hsnDescription = hsnDescription;
	}

	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public String getProductAttributeId() {
		return productAttributeId;
	}

	public void setProductAttributeId(String productAttributeId) {
		this.productAttributeId = productAttributeId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public String getGroupId() {
		return groupId;
	}

	public String getBrandId() {
		return brandId;
	}

	public String getGenericId() {
		return genericId;
	}

	public String getModelId() {
		return modelId;
	}
	
}
