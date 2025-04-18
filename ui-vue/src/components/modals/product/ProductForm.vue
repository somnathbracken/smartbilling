<template>
    <div v-show="showForm" class="mb-6 p-4 border rounded bg-gray-50">
      <h3 class="text-lg font-semibold mb-4">
        {{ selectedProduct ? 'Edit Product' : 'Add Product' }}
      </h3>
      <h2 class="text-2xl font-bold mb-4">Product Master</h2>
      <form @submit.prevent="saveProduct" class="grid grid-cols-2 gap-4">
        <!-- These are all input fields for the form -->
        <!-- Basic Details -->
          <div class="col-span-4 text-lg font-semibold mt-4 mb-2">Basic Details</div>
          <div>
            <label>Product Code</label>
            <input v-model="product.productCode" class="input" type="text" />
          </div>
          <div>
            <label>Product Name</label>
            <input v-model="product.name" class="input" type="text" />
          </div>

          <!-- Inventory Details -->
          <div class="col-span-4 text-lg font-semibold mt-4 mb-2">Inventory Details</div>
          <div>
            <label>Site</label>
            <div class="flex space-x-2 items-center">
              <select v-model="product.siteId" class="input">
                <option v-for="site in sites" :key="site.id" :value="site.id">{{ site.name }}</option>
              </select>
              <button type="button" class="btn" @click="showSiteModal = true">+</button>
            </div>
          </div>
          <div>
            <label>Stock Location</label>
            <div class="flex space-x-2 items-center">
              <select v-model="product.stockId" class="input">
                <option v-for="stock in stocks" :key="stock.id" :value="stock.id">{{ stock.name }}</option>
              </select>
              <button type="button" class="btn" @click="showStockModal = true">+</button>
            </div>
          </div>
          <div>
            <label>Warehouse</label>
            <div class="flex space-x-2 items-center">
              <select v-model="product.warehouseId" class="input">
                <option v-for="warehouse in warehouses" :key="warehouse.id" :value="warehouse.id">{{ warehouse.name }}</option>
              </select>
              <button type="button" class="btn" @click="showWarehouseModal = true">+</button>
            </div>
          </div>
          <!-- Classification -->
          <div class="col-span-4 text-lg font-semibold mt-4 mb-2">Classification</div>
          <div>
            <label>Category</label>
            <div class="flex items-center space-x-2">
              <select v-model="product.categoryId" class="input">
                <option v-for="cat in categories" :key="cat.id" :value="cat.id">{{ cat.name }}</option>
              </select>
              <button type="button" class="btn" @click="showCategoryModal = true">+</button>
            </div>
          </div>
          <div>
            <label>Group</label>
            <div class="flex items-center space-x-2">
              <select v-model="product.groupId" class="input">
                <option v-for="item in groups" :key="item.id" :value="item.id">{{ item.name }}</option>
              </select>
              <button type="button" class="btn" @click="showGroupModal = true">+</button>
            </div>
          </div>
          <div>
            <label>Brand</label>
            <div class="flex items-center space-x-2">
              <select v-model="product.brandId" class="input">
                <option v-for="item in brands" :key="item.id" :value="item.id">{{ item.name }}</option>
              </select>
              <button type="button" class="btn" @click="showBrandModal = true">+</button>
            </div>
          </div>
          <div>
            <label>Generic Product</label>
            <div class="flex items-center space-x-2">
              <select v-model="product.genericId" class="input">
                <option v-for="item in generics" :key="item.id" :value="item.id">{{ item.name }}</option>
              </select>
              <button type="button" class="btn" @click="showGenericModal = true">+</button>
            </div>
          </div>
          <div>
            <label>UOM</label>
            <div class="flex space-x-2 items-center">
              <select v-model="product.unitOfMeasure" class="input">
                <option v-for="uom in uoms" :key="uom.id" :value="uom.id">{{ uom.name }}</option>
              </select>
              <button type="button" class="btn" @click="showUomModal = true">+</button>
            </div>
            </div>
          <!-- Descriptions -->
          <div class="col-span-4 text-lg font-semibold mt-4 mb-2">Descriptions</div>
          <div class="col-span-4">
            <label>Product Description</label>
            <textarea v-model="product.description" class="input"></textarea>
          </div>

          <!-- Pricing & SKU -->
          <div class="col-span-4 text-lg font-semibold mt-4 mb-2">Pricing & SKU</div>
          <div>
            <label>Price</label>
            <input v-model="product.pruchasePrice" class="input" type="number" step="0.01" />
          </div>
          <div>
            <label>MRP</label>
            <input v-model="product.mrp" class="input" type="number" step="0.01" />
          </div>
          <div>
            <label>Pack Size</label>
            <input v-model="product.packSize" class="input" type="number" />
          </div>
          <div>
            <label>Quantity</label>
            <input v-model="product.quantity" class="input" type="number" />
          </div>          
          <div>
            <label>Average Cost</label>
            <input v-model="product.averageCost" class="input" type="number" step="0.01" />
          </div>
          <div>
            <label>Single Unit Product Code</label>
            <input v-model="product.singleUnitProductCode" class="input" type="text" />
          </div>

          <!-- Tax & Compliance -->
          <div class="col-span-4 text-lg font-semibold mt-4 mb-2">Tax & Compliance</div>
          <div>
            <label>HSN Code</label>
            <input v-model="product.hsnCode" class="input" type="text" />
          </div>
          <div>
            <label>HSN Description</label>
            <input v-model="product.hsnDescription" class="input" type="text" />
          </div>
          <div>
            <label>Model/Part ID</label>
            <input v-model="product.modelId" class="input" type="text" />
          </div>
          <div>
            <label>Warranty Terms</label>
            <input v-model="product.warrantyTerms" class="input" type="text" />
          </div>
          <div>
            <label>Product Lot</label>
            <div class="flex items-center space-x-2">
              <select v-model="product.productLotId" class="input">
                <option v-for="lot in productLots" :key="lot.id" :value="lot.id">
                  {{ lot.lot_code }} (MFG: {{ lot.dateManufactured }} / EXP: {{ lot.dateExpiry }})
                </option>
              </select>
              <button type="button" class="btn" @click="showLotModal = true">+</button>
            </div>
          </div>

          <div>
            <label>GST Tax</label>
            <div class="flex items-center space-x-2">
              <select v-model="product.gstPercentage" class="input">
                <option v-for="gst in gstTaxes" :key="gst.id" :value="gst.id">{{ gst.name }} ({{ gst.percentage }}%)</option>
              </select>
              <button type="button" class="btn" @click="showGstModal = true">+</button>
            </div>
          </div>
          <!-- Associations -->
          <div class="col-span-4 text-lg font-semibold mt-4 mb-2">Associations</div>
          <div>
            <!-- <label>Supplier</label>
            <select v-model="product.supplierId" class="input">
              <option value="">Select Supplier</option>
              <option v-for="supplier in suppliers" :key="supplier.id" :value="supplier.id">{{ supplier.name }}</option>
            </select> -->
            <label for="vendor">Vendor</label>
            <select v-model="product.vendorId" class="form-select border p-2 rounded w-full">
              <option disabled value="">Select Vendor</option>
              <option v-for="vendor in vendors" :key="vendor.id" :value="vendor.id">
                {{ vendor.name }}
              </option>
            </select>
          </div>
          <div>
            <label>Purchase Order</label>
            <select v-model="product.purchaseOrderId" class="input">
              <option value="">Select Purchase Order</option>
              <option v-for="po in purchaseOrders" :key="po.id" :value="po.id">{{ po.name }}</option>
            </select>
          </div>
          <div>
            <label>Product Attribute</label>
            <select v-model="product.productAttributeId" class="input">
              <option value="">Select Attribute</option>
              <option v-for="attr in productAttributes" :key="attr.id" :value="attr.id">{{ attr.name }}</option>
            </select>
          </div>

          <!-- Discounts -->
          <div class="col-span-4 text-lg font-semibold mt-4 mb-2">Discounts</div>
          <div>
            <label>Discounts(%)</label>
            <input v-model="product.discounts" class="input" type="number" step="0.01" />
          </div>

          <!-- Flags & Status -->
          <div class="col-span-4 text-lg font-semibold mt-4 mb-2">Flags & Status</div>
          <!-- <div class="flex items-center">
            <label class="mr-2">Has Attributes</label>
            <input type="checkbox" v-model="product.has_attributes" />
          </div>
          <div class="flex items-center">
            <label class="mr-2">Has Lots</label>
            <input type="checkbox" v-model="product.has_lots" />
          </div>
          <div class="flex items-center">
            <label class="mr-2">Has Instances</label>
            <input type="checkbox" v-model="product.has_instances" />
          </div> -->
          <div class="flex items-center">
            <label class="mr-2">Is Active</label>
            <input type="checkbox" v-model="product.isActive" />
          </div>
          <div class="flex items-center">
            <label class="mr-2">Deleted</label>
            <input type="checkbox" v-model="product.deleted" />
          </div>

          <!-- Actions -->
          <div class="col-span-4 flex justify-end space-x-4 mt-6 space-x-2">
            <button type="submit" class="bg-green-600 text-white px-6 py-2 rounded hover:bg-green-700">Save</button>
            <button type="button" @click="resetForm" class="bg-gray-500 text-white px-6 py-2 rounded hover:bg-gray-600">Reset</button>
          </div>
        <!-- ----------------------------------------------------------------------------------- -->
      </form>
    </div>

      <!-- ===================   Starting Modal Definition =================== -->

    <!-- Modals -->
    <SiteModal
      :show="showSiteModal"
      @close="showSiteModal = false"
      @added="() => {
        getAllSites()
          .then(response => {
            sites.value = response.data;
            const lastSite = sites.value[sites.value.length - 1];
            if (lastSite) {
              product.site_id = lastSite.id;
            }
          })
          .catch(error => {
            console.error('Error fetching updated sites:', error);
          });
      }"
    />

    <StockModal
      :show="showStockModal"
      @close="showStockModal = false"
      @added="() => {
        getAllStocks()
          .then(response => {
            stocks.value = response.data;
            const lastStock = stocks.value[stocks.value.length - 1];
            if (lastStock) {
              product.stock_id = lastStock.id;
            }
          })
          .catch(error => {
            console.error('Error fetching updated Stocks:', error);
          });
      }"
    />

<WarehouseModal
  :show="showWarehouseModal"
  @close="showWarehouseModal = false"
  @added="() => {
    getAllWarehouses()
      .then(response => {
        warehouses.value = response.data;
        const lastWarehouse = warehouses.value[warehouses.value.length - 1];
        if (lastWarehouse) {
          product.warehouse_id = lastWarehouse.id;
        }
      })
      .catch(error => {
        console.error('Error fetching warehouse:', error);
      });
  }"
/>
<UomModal
  :show="showUomModal"
  @close="showUomModal = false"
  @added="() => {
    getAllUoms()
      .then(response => {
        uoms.value = response.data;
        const lastUoms = uoms.value[uoms.value.length - 1];
        if (lastUoms) {
          product.uom_id = lastUoms.id;
        }
      })
      .catch(error => {
        console.error('Error fetching UOM:', error);
      });
  }"
/>
<CategoryModal
  :show="showCategoryModal"
  @close="showCategoryModal = false"
  @added="() => {
    getAllCategories()
      .then(response => {
        categories.value = response.data;
        const lastCategories = categories.value[categories.value.length - 1];
        if (lastUoms) {
          product.category_id = lastCategories.id;
        }
      })
      .catch(error => {
        console.error('Error fetching UOM:', error);
      });
  }"
/>
    <GenericProductModal :isOpen="showGenericModal" @close="showGenericModal = false" @save="handleGenericSaved" />    
    <GroupModal :show="showGroupModal" @close="showGroupModal = false" @saved="handleGroupSaved" />
    <BrandModal :show="showBrandModal" @close="showBrandModal = false" @saved="handleBrandSaved" />
    <GstTaxModal :show="showGstModal" @close="showGstModal = false" @saved="handleGSTTaxSaved"/>
    <ProductLotModal :show="showLotModal" :productAttributes="productAttributes" @close="showLotModal = false" @saved="handleLotSaved"/>
  </template>
  
  <script setup>
  defineProps(['product', 'selectedProduct', 'showForm', 'sites', 'stocks', 'warehouses', 'categories', 'groups', 'brands', 'generics', 'uoms', 'productLots', 'gstTaxes', 'vendors', 'purchaseOrders', 'productAttributes'])
  defineEmits(['save-product'])
  
  // const saveProduct = () => {
  //   // emit event with updated product
  //   emit('save-product')
  // }


//const componentKey = ref(0)
import { ref, computed, onMounted } from 'vue'
import axios from 'axios'

import { createProduct, getAllProducts, getAllBrands, getAllCategories, getAllGroups, getAllSites, getAllStocks, getAllUoms, getAllWarehouses, getAllGenericProducts, getAllTaxes, getAllProductLots, getAllVendors } from '../../../services/ProductService';


// const purchaseOrders = ref([])
// const showTaxes = ref(false)

// ---------------------------- Modal & Dropdown Loading started ----------------------------------

// Site related dropdown and Modal
const showSiteModal = ref(false)
import SiteModal from './SiteModal.vue'
const sites = ref([]);
onMounted(() => {
  loadSites();
});

const loadSites = async () => {
  try {
    const response = await getAllSites();
    sites.value = response.data;
  } catch (error) {
    console.error('Failed to load sites:', error);
  }
};

// Load Stocks in modal dropdown
import StockModal from './StockModal.vue'
const showStockModal = ref(false)
const stocks = ref([]);
onMounted(() => {
  loadStocks();
});

const loadStocks = async () => {
  try {
    const response = await getAllStocks();
    stocks.value = response.data;
  } catch (error) {
    console.error('Failed to load stocks:', error);
  }
};

// Load Warehouse in modal dropdown
import WarehouseModal from './WarehouseModal.vue'
const showWarehouseModal = ref(false)
const warehouses = ref([]);
onMounted(() => {
  loadWarehouses();
});

const loadWarehouses = async () => {
  try {
    const response = await getAllWarehouses();
    warehouses.value = response.data;
  } catch (error) {
    console.error('Failed to load warehouses:', error);
  }
};

// Load Group in modal dropdown
import GroupModal from './GroupModal.vue'
const showGroupModal = ref(false)
const groups= ref([]);
onMounted(() => {
  loadGroups();
});

const loadGroups = async () => {
  try {
    const response = await getAllGroups();
    groups.value = response.data;
  } catch (error) {
    console.error('Failed to load Product Groups:', error);
  }
};

// Load UOMS in modal dropdown
import UomModal from './UomModal.vue'
const showUomModal = ref(false)
const uoms = ref([]);
onMounted(() => {
  loadUoms();
});

const loadUoms = async () => {
  try {
    const response = await getAllUoms();
    uoms.value = response.data;
  } catch (error) {
    console.error('Failed to load warehouses:', error);
  }
};

// Load Category in modal dropdown
import CategoryModal from './CategoryModal.vue'
const showCategoryModal = ref(false)
const categories = ref([]);
onMounted(() => {
  loadCategories();
});

const loadCategories = async () => {
  try {
    const response = await getAllCategories();
    categories.value = response.data;
  } catch (error) {
    console.error('Failed to load Categories:', error);
  }
};

// Load Brand in modal dropdown
import BrandModal from './BrandModal.vue'
const showBrandModal = ref(false)
const brands = ref([]);
onMounted(() => {
  loadBrands();
});

const loadBrands = async () => {
  try {
    const response = await getAllBrands();
    brands.value = response.data;
  } catch (error) {
    console.error('Failed to load Brands:', error);
  }
};

// Load Generic Product in modal dropdown
import GenericProductModal from './GenericProductModal.vue'
const showGenericModal = ref(false)
const generics = ref([]);
onMounted(() => {
  loadGenerics();
});

const loadGenerics = async () => {
  try {
    const response = await getAllGenericProducts();
    generics.value = response.data;
  } catch (error) {
    console.error('Failed to load Generics:', error);
  }
};

// Load Taxes in modal dropdown
import GstTaxModal from './GstTaxModal.vue'
const showGstModal = ref(false)
const gstTaxes = ref([]);
onMounted(() => {
  loadTaxes();
});

const loadTaxes = async () => {
  try {
    const response = await getAllTaxes();
    gstTaxes.value = response.data;
  } catch (error) {
    console.error('Failed to load Taxes:', error);
  }
};


// Load Product Lots in modal dropdown
import ProductLotModal from './ProductLotModal.vue'
const showLotModal = ref(false)
const productLots = ref([]);
onMounted(() => {
  loadProductLots();
});

const loadProductLots = async () => {
  try {
    const response = await getAllProductLots();
    productLots.value = response.data;
  } catch (error) {
    console.error('Failed to load Product Lots:', error);
  }
};

const vendors = ref([])

onMounted(async () => {
  try {
    const response = await getAllVendors()
    vendors.value = response.data
  } catch (error) {
    console.error('Error fetching vendors:', error)
  }
})

//----------------------- Modal & dropdown Loading Finished Here ------------------------------------

//----------------------- Modal Data Save Started Here ------------------------------------

//----------------------- Modal Data Save Finished Here ------------------------------------
function handleBrandSaved(brand) {
  showBrandModal.value = false
}

function handleLotSaved(lot) {
  showLotModal.value = false
}

function handleGSTTaxSaved(gst) {
  showGstModal.value = false
}

function handleGroupSaved(group) {
  showGroupModal.value = false
}
function handleCategorySaved(category) {
  showCategoryModal.value = false
}
const handleGenericSaved = (data) => {
  showGenericModal.value = false
}
const handleTaxSaved = (data) => {
  showTaxes.value = false
}
//----------------------- Product Definition started here -----------------------------------
const product = ref({
productCode: null,
hsnCode: null,
hsnDescription: null,
genericId: null,
modelId: null,
name: null,
description: null,
pruchasePrice: 0,
mrp: 0,
hasInstances: null,
hasLots: null,
hasAttributes: null,
// defaultUom: null,
packSize: 0,
averageCost: 0,
singleUnitProductCode: null,
dimensionGroup: null,
lotInformation: 0,
warrantyTerms: null,
sku: null,
gstPercentage: 0,
unitOfMeasure: null,
vendorId: null,
siteId: null,
warehouseId: null,
categoryId: null,
groupId: null,
brandId: null,
stockId: null,
uomId: null,
productLotId: null,
discounts: 0,
quantity: 0,
purchaseOrderId: null,
productAttributeId: null,
isActive: true,
deleted: false
})

const resetForm = () => {
  product.value = { productcode: '' }
}

const saveProduct = async () => {
  alert('Saving Product!')
  console.log("Saving Product:", JSON.stringify(product.value, null, 2))
  if (product.value.id) {
    // Update existing product
    await updateProduct(product.value.id, product.value)
  } else {
    // Create new product
    //alert('calling createProduct')
    await createProduct(product.value)
  }

  await loadProducts()
  resetForm()
  //showForm.value = false
  alert('Product saved!')
}
const loadProducts = async () => {
  try {
    const res = await getAllProducts()
    // Make sure it's an array
    product.value = Array.isArray(res.data) ? res.data : []
  } catch (err) {
    console.error('Failed to load products:', err)
    product.value = []
  }
}
  </script>
  
  <style scoped>
  /* Tailwind enhanced utility classes */
  .input {
  @apply border border-gray-300 px-3 py-1.5 text-sm rounded-md w-full 
         bg-white shadow-inner focus:outline-none focus:ring-2 
         focus:ring-blue-300 focus:border-blue-400 
         transition duration-200 ease-in-out;
}

.btn {
  @apply bg-gradient-to-r from-blue-500 to-blue-600 text-white text-sm 
         px-4 py-1.5 rounded-md hover:from-blue-600 hover:to-blue-700 
         active:from-blue-700 active:to-blue-800 shadow-md 
         transition duration-200 ease-in-out;
}
.select {
  @apply border border-gray-300 px-3 py-1.5 text-sm rounded-md w-full 
         bg-white shadow-inner focus:outline-none focus:ring-2 
         focus:ring-blue-300 focus:border-blue-400 
         transition duration-200 ease-in-out appearance-none;
}
  </style>
  