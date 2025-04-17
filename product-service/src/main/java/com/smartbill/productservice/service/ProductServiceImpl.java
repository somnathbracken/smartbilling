package com.smartbill.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartbill.productservice.model.Brand;
import com.smartbill.productservice.model.Category;
import com.smartbill.productservice.model.Product;
import com.smartbill.productservice.model.ProductLot;
import com.smartbill.productservice.model.Site;
import com.smartbill.productservice.model.Stock;
import com.smartbill.productservice.model.Uom;
import com.smartbill.productservice.model.Vendor;
import com.smartbill.productservice.model.Warehouse;
import com.smartbill.productservice.repository.BrandRepository;
import com.smartbill.productservice.repository.CategoryRepository;
import com.smartbill.productservice.repository.ProductLotRepository;
import com.smartbill.productservice.repository.ProductRepository;
import com.smartbill.productservice.repository.SiteRepository;
import com.smartbill.productservice.repository.StockRepository;
import com.smartbill.productservice.repository.UomRepository;
import com.smartbill.productservice.repository.VendorRepository;
import com.smartbill.productservice.repository.WarehouseRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired private ProductRepository productRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private BrandRepository brandRepository;
    @Autowired private VendorRepository vendorRepository;
    @Autowired private SiteRepository siteRepository;
    @Autowired private WarehouseRepository warehouseRepository;
    @Autowired private StockRepository stockRepository;
    @Autowired private UomRepository uomRepository;
    @Autowired private ProductLotRepository productLotRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public List<Uom> getAllUoms() {
        return uomRepository.findAll();
    }

    @Override
    public List<ProductLot> getAllProductLots() {
        return productLotRepository.findAll();
    }
}
