package com.smartbill.productservice.controller;

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

import com.smartbill.productservice.model.Brand;
import com.smartbill.productservice.model.Category;
import com.smartbill.productservice.model.GenericProduct;
import com.smartbill.productservice.model.Group;
import com.smartbill.productservice.model.Product;
import com.smartbill.productservice.model.ProductLot;
import com.smartbill.productservice.model.Site;
import com.smartbill.productservice.model.Stock;
import com.smartbill.productservice.model.Tax;
import com.smartbill.productservice.model.Uom;
import com.smartbill.productservice.model.Vendor;
import com.smartbill.productservice.model.Warehouse;
import com.smartbill.productservice.repository.BrandRepository;
import com.smartbill.productservice.repository.CategoryRepository;
import com.smartbill.productservice.repository.GenericProductRepository;
import com.smartbill.productservice.repository.GroupRepository;
import com.smartbill.productservice.repository.ProductLotRepository;
import com.smartbill.productservice.repository.ProductRepository;
import com.smartbill.productservice.repository.SiteRepository;
import com.smartbill.productservice.repository.StockRepository;
import com.smartbill.productservice.repository.TaxRepository;
import com.smartbill.productservice.repository.UomRepository;
import com.smartbill.productservice.repository.VendorRepository;
import com.smartbill.productservice.repository.WarehouseRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow requests from frontend
public class ProductController {

    @Autowired private ProductRepository productRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private BrandRepository brandRepository;
    @Autowired private VendorRepository vendorRepository;
    @Autowired private SiteRepository siteRepository;
    @Autowired private WarehouseRepository warehouseRepository;
    @Autowired private StockRepository stockRepository;
    @Autowired private UomRepository uomRepository;
    @Autowired private ProductLotRepository productLotRepository;
    @Autowired private GroupRepository groupRepository;
    @Autowired private GenericProductRepository genericProductRepository;
    @Autowired private TaxRepository taxRepository;
//    @Autowired private ProductService productService;

    // ========== Product CRUD ==========

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/create-product")
    public Product createProduct(@RequestBody Product product) {
    	System.out.println("Product Received: " + product); // This should print!
    	System.out.println("Product code:: "+ product.getProductCode());
        return productRepository.save(product);
    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable Integer id) {
         Product product = productRepository.findById(id).get();
         product.setDeleted(true);
        productRepository.save(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @GetMapping("/products/search")
    public List<Product> searchProducts(@RequestParam String keyword) {
        return productRepository.findByNameContainingIgnoreCaseOrProductCodeContainingIgnoreCaseOrBarCodeContainingIgnoreCase(keyword, keyword, keyword);
    }

    // ========== Related Entities ==========

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/brands")
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    @GetMapping("/vendors")
    public List<Vendor> getVendors() {
        return vendorRepository.findAll();
    }

    @GetMapping("/sites")
    public List<Site> getSites() {
        return siteRepository.findAll();
    }

    @GetMapping("/warehouses")
    public List<Warehouse> getWarehouses() {
        return warehouseRepository.findAll();
    }

    @GetMapping("/stocks")
    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }

    @GetMapping("/uoms")
    public List<Uom> getUoms() {
        return uomRepository.findAll();
    }

    @GetMapping("/product-lots")
    public List<ProductLot> getProductLots() {
        return productLotRepository.findAll();
    }
    
    @GetMapping("/groups")
    public List<Group> getGroups() {
        return groupRepository.findAll();
    }
    
    @GetMapping("/generic-products")
    public List<GenericProduct> getGenericProducts() {
        return genericProductRepository.findAll();
    }
    @GetMapping("/taxes")
    public List<Tax> getTaxes() {
        return taxRepository.findAll();
    }
}
