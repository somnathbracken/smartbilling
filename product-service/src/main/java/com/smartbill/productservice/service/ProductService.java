package com.smartbill.productservice.service;

import java.util.List;

import com.smartbill.productservice.model.Brand;
import com.smartbill.productservice.model.Category;
import com.smartbill.productservice.model.Product;
import com.smartbill.productservice.model.ProductLot;
import com.smartbill.productservice.model.Site;
import com.smartbill.productservice.model.Stock;
import com.smartbill.productservice.model.Uom;
import com.smartbill.productservice.model.Vendor;
import com.smartbill.productservice.model.Warehouse;

public interface ProductService {

    // Product
    List<Product> getAllProducts();
    Product saveProduct(Product product);
    Product getProductById(Integer id);

    // Related entities
    List<Category> getAllCategories();
    List<Brand> getAllBrands();
    List<Vendor> getAllVendors();
    List<Site> getAllSites();
    List<Warehouse> getAllWarehouses();
    List<Stock> getAllStocks();
    List<Uom> getAllUoms();
    List<ProductLot> getAllProductLots();
}
