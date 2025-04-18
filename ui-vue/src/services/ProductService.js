// export async function fetchAllProducts() {
//   return [
//     { id: 1, name: 'Product A', sku: 'A100', unit_price: 100, quantity: 10, status: 'active' },
//     { id: 2, name: 'Product B', sku: 'B200', unit_price: 200, quantity: 5, status: 'inactive' }
//   ]
// }

// export async function saveProductData(product) {
//   console.log('Saving', product)
// }

// export async function fetchCategories() {
//   return [
//     { id: 1, name: 'Electronics' },
//     { id: 2, name: 'Apparel' }
//   ]
// }

// src/services/productService.js

import axios from 'axios';

const API_BASE = 'http://localhost:8082/api'; // Adjust if needed

// ----------------- PRODUCT -----------------
export const getAllProducts = () => axios.get(`${API_BASE}/products`);
export const getProductById = (id) => axios.get(`${API_BASE}/products/${id}`);
export const createProduct = (data) => axios.post(`${API_BASE}/create-product`, data, {
    headers: {
      'Content-Type': 'application/json'
    }
  });
export const updateProductById = (id, data) => axios.put(`${API_BASE}/product/${id}`, data);
export const deleteProductById = (id) => axios.delete(`${API_BASE}/product/${id}`);

// ----------------- CATEGORY -----------------
export const getAllCategories = () => axios.get(`${API_BASE}/categories`);
export const createCategory = (data) => axios.post(`${API_BASE}/categories`, data);

// ----------------- GROUP -----------------
export const getAllGroups = () => axios.get(`${API_BASE}/groups`);
export const createGroup = (data) => axios.post(`${API_BASE}/groups`, data);

// ----------------- BRAND -----------------
export const getAllBrands = () => axios.get(`${API_BASE}/brands`);
export const createBrand = (data) => axios.post(`${API_BASE}/brands`, data);

// ----------------- MANUFACTURER -----------------
export const getAllManufacturers = () => axios.get(`${API_BASE}/manufacturers`);
export const createManufacturer = (data) => axios.post(`${API_BASE}/manufacturers`, data);

// ----------------- UOM -----------------
export const getAllUoms = () => axios.get(`${API_BASE}/uoms`);
export const createUom = (data) => axios.post(`${API_BASE}/uoms`, data);

// ----------------- LOT -----------------
export const getAllProductLots = () => axios.get(`${API_BASE}/product-lots`);
export const createProductLot = (data) => axios.post(`${API_BASE}/product-lots`, data);

// ----------------- STOCK -----------------
export const getAllStocks = () => axios.get(`${API_BASE}/stocks`);
export const createStock = (data) => axios.post(`${API_BASE}/stocks`, data);

// ----------------- WAREHOUSE -----------------
export const getAllWarehouses = () => axios.get(`${API_BASE}/warehouses`);
export const createWarehouse = (data) => axios.post(`${API_BASE}/warehouses`, data);

// ----------------- SITE -----------------
export const getAllSites = () => axios.get(`${API_BASE}/sites`);
export const createSite = (data) => axios.post(`${API_BASE}/sites`, data);

// ----------------- GENERIC PRODUCT -----------------
export const getAllGenericProducts = () => axios.get(`${API_BASE}/generic-products`);
export const createGenericProduct = (data) => axios.post(`${API_BASE}/generic-products`, data);

// ----------------- PRODUCT ATTRIBUTE -----------------
export const getAllAttributes = () => axios.get(`${API_BASE}/attributes`);
export const createAttribute = (data) => axios.post(`${API_BASE}/attributes`, data);

// ----------------- PRODUCT ATTRIBUTE VALUE -----------------
export const getAllAttributeValues = () => axios.get(`${API_BASE}/attribute-values`);
export const createAttributeValue = (data) => axios.post(`${API_BASE}/attribute-values`, data);

// ----------------- PRODUCT PRICE -----------------
export const getAllProductPrices = () => axios.get(`${API_BASE}/product-prices`);
export const createProductPrice = (data) => axios.post(`${API_BASE}/product-prices`, data);

// ----------------- VENDOR (Supplier) -----------------
export const getAllVendors = () => axios.get(`${API_BASE}/vendors`);
export const createVendor = (data) => axios.post(`${API_BASE}/vendors`, data);

// ----------------- PURCHASE ORDER -----------------
export const getAllPurchaseOrders = () => axios.get(`${API_BASE}/purchase-orders`);
export const createPurchaseOrder = (data) => axios.post(`${API_BASE}/purchase-orders`, data);

// ----------------- Taxes -----------------
export const getAllTaxes = () => axios.get(`${API_BASE}/taxes`);
export const createTaxes = (data) => axios.post(`${API_BASE}/taxes`, data);
