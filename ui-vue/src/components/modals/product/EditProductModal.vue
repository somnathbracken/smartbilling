<template>
  <div class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
    <div class="bg-white w-[90%] max-w-2xl rounded-xl shadow-lg max-h-[90vh] overflow-y-auto p-6 relative">
      <h2 class="text-xl font-semibold mb-4">Edit Product</h2>
      <form @submit.prevent="update">
        <!-- your form fields using localProduct -->


      <!-- ------------------------------Input Fields Details ----------------------------------------------------- -->
      <!-- Basic Details -->
      <div class="col-span-4 text-lg font-semibold mt-4 mb-2">Basic Details</div>
          <div>
            <label>Product Code</label>
            <input v-model="localProduct.productCode" class="input" type="text" />
          </div>
          <div>
            <label>Product Name</label>
            <input v-model="localProduct.name" class="input" type="text" />
          </div>

          <!-- Inventory Details -->
          <div class="col-span-4 text-lg font-semibold mt-4 mb-2">Inventory Details</div>
          <div>
            <label>Site</label>
            <div class="flex space-x-2 items-center">
              <select v-model="localProduct.siteId" class="input">
                <option v-for="site in sites" :key="site.id" :value="site.id">{{ site.name }}</option>
              </select>
            </div>
          </div>
          <div>
            <label>Stock Location</label>
            <div class="flex space-x-2 items-center">
              <select v-model="localProduct.stockId" class="input">
                <option v-for="stock in stocks" :key="stock.id" :value="stock.id">{{ stock.name }}</option>
              </select>
            </div>
          </div>
          <div>
            <label>Warehouse</label>
            <div class="flex space-x-2 items-center">
              <select v-model="localProduct.warehouseId" class="input">
                <option v-for="warehouse in warehouses" :key="warehouse.id" :value="warehouse.id">{{ warehouse.name }}</option>
              </select>
            </div>
          </div>
          <!-- Classification -->
          <div class="col-span-4 text-lg font-semibold mt-4 mb-2">Classification</div>
          <div>
            <label>Category</label>
            <div class="flex items-center space-x-2">
              <select v-model="localProduct.categoryId" class="input">
                <option v-for="cat in categories" :key="cat.id" :value="cat.id">{{ cat.name }}</option>
              </select>
            </div>
          </div>
          <div>
            <label>Group</label>
            <div class="flex items-center space-x-2">
              <select v-model="localProduct.groupId" class="input">
                <option v-for="item in groups" :key="item.id" :value="item.id">{{ item.name }}</option>
              </select>
            </div>
          </div>
          <div>
            <label>Brand</label>
            <div class="flex items-center space-x-2">
              <select v-model="localProduct.brandId" class="input">
                <option v-for="item in brands" :key="item.id" :value="item.id">{{ item.name }}</option>
              </select>
            </div>
          </div>
          <div>
            <label>Generic Product</label>
            <div class="flex items-center space-x-2">
              <select v-model="localProduct.genericId" class="input">
                <option v-for="item in generics" :key="item.id" :value="item.id">{{ item.name }}</option>
              </select>
            </div>
          </div>
          <div>
            <label>UOM</label>
            <div class="flex space-x-2 items-center">
              <select v-model="localProduct.unitOfMeasure" class="input">
                <option v-for="uom in uoms" :key="uom.id" :value="uom.id">{{ uom.name }}</option>
              </select>
            </div>
            </div>
          <!-- Descriptions -->
          <div class="col-span-4 text-lg font-semibold mt-4 mb-2">Descriptions</div>
          <div class="col-span-4">
            <label>Product Description</label>
            <textarea v-model="localProduct.description" class="input"></textarea>
          </div>

          <!-- Pricing & SKU -->
          <div class="col-span-4 text-lg font-semibold mt-4 mb-2">Pricing & SKU</div>
          <div>
            <label>Price</label>
            <input v-model="localProduct.pruchasePrice" class="input" type="number" step="0.01" />
          </div>
          <div>
            <label>MRP</label>
            <input v-model="localProduct.mrp" class="input" type="number" step="0.01" />
          </div>
          <div>
            <label>Pack Size</label>
            <input v-model="localProduct.packSize" class="input" type="number" />
          </div>
          <div>
            <label>Quantity</label>
            <input v-model="localProduct.quantity" class="input" type="number" />
          </div>          
          <div>
            <label>Average Cost</label>
            <input v-model="localProduct.averageCost" class="input" type="number" step="0.01" />
          </div>
          <div>
            <label>Single Unit Product Code</label>
            <input v-model="localProduct.singleUnitProductCode" class="input" type="text" />
          </div>

          <!-- Tax & Compliance -->
          <div class="col-span-4 text-lg font-semibold mt-4 mb-2">Tax & Compliance</div>
          <div>
            <label>HSN Code</label>
            <input v-model="localProduct.hsnCode" class="input" type="text" />
          </div>
          <div>
            <label>HSN Description</label>
            <input v-model="localProduct.hsnDescription" class="input" type="text" />
          </div>
          <div>
            <label>Model/Part ID</label>
            <input v-model="localProduct.modelId" class="input" type="text" />
          </div>
          <div>
            <label>Warranty Terms</label>
            <input v-model="localProduct.warrantyTerms" class="input" type="text" />
          </div>
          <div>
            <label>Product Lot</label>
            <div class="flex items-center space-x-2">
              <select v-model="localProduct.productLotId" class="input">
                <option v-for="lot in productLots" :key="lot.id" :value="lot.id">
                  {{ lot.lot_code }} (MFG: {{ lot.dateManufactured }} / EXP: {{ lot.dateExpiry }})
                </option>
              </select>
            </div>
          </div>

          <div>
            <label>GST Tax</label>
            <div class="flex items-center space-x-2">
              <select v-model="localProduct.gstPercentage" class="input">
                <option v-for="gst in gstTaxes" :key="gst.id" :value="gst.id">{{ gst.name }} ({{ gst.percentage }}%)</option>
              </select>
            </div>
          </div>
          <!-- Associations -->
          <div class="col-span-4 text-lg font-semibold mt-4 mb-2">Associations</div>
          <div>
            <!-- <label>Supplier</label>
            <select v-model="localProduct.supplierId" class="input">
              <option value="">Select Supplier</option>
              <option v-for="supplier in suppliers" :key="supplier.id" :value="supplier.id">{{ supplier.name }}</option>
            </select> -->
            <label for="vendor">Vendor</label>
            <select v-model="localProduct.vendorId" class="form-select border p-2 rounded w-full">
              <option disabled value="">Select Vendor</option>
              <option v-for="vendor in vendors" :key="vendor.id" :value="vendor.id">
                {{ vendor.name }}
              </option>
            </select>
          </div>
          <div>
            <label>Purchase Order</label>
            <select v-model="localProduct.purchaseOrderId" class="input">
              <option value="">Select Purchase Order</option>
              <option v-for="po in purchaseOrders" :key="po.id" :value="po.id">{{ po.name }}</option>
            </select>
          </div>
          <div>
            <label>Product Attribute</label>
            <select v-model="localProduct.productAttributeId" class="input">
              <option value="">Select Attribute</option>
              <option v-for="attr in productAttributes" :key="attr.id" :value="attr.id">{{ attr.name }}</option>
            </select>
          </div>

          <!-- Discounts -->
          <div class="col-span-4 text-lg font-semibold mt-4 mb-2">Discounts</div>
          <div>
            <label>Discounts(%)</label>
            <input v-model="localProduct.discounts" class="input" type="number" step="0.01" />
          </div>

          <!-- Flags & Status -->
          <div class="col-span-4 text-lg font-semibold mt-4 mb-2">Flags & Status</div>
          <div class="flex items-center">
            <label class="mr-2">Is Active</label>
            <input type="checkbox" v-model="localProduct.isActive" />
          </div>
        <!-- ----------------------------------------------------------------------------------- -->


        <!-- other fields -->
        <div class="mt-6 flex justify-end space-x-3">
          <button type="button" class="btn-red" @click="$emit('close')">Cancel</button>
          <button type="submit" class="btn">Update</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, toRaw, onMounted } from 'vue'
import axios from 'axios'
import { updateProductById, getAllBrands, getAllCategories, getAllGroups, getAllSites, getAllStocks, getAllUoms, getAllWarehouses, getAllGenericProducts, getAllTaxes, getAllProductLots, getAllVendors } from '../../../services/ProductService';
// Props and Emits
const props = defineProps({ product: Object })
const emit = defineEmits(['close', 'update'])

const localProduct = ref({ ...toRaw(props.product) })

const update = async () => {
  try {
    alert('calling update')
    await updateProductById(`${localProduct.value.id}`,localProduct.value) //axios.put(`/api/products/${localProduct.value.id}`, localProduct.value)
    emit('update', localProduct.value)
  } catch (err) {
    console.error(err)
  }
}

// --------------------------------------------- Dropdown loading ------------------------------------
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
// --------------------------------------------- Dropdown loading Finished ------------------------------------
</script>
<style>
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