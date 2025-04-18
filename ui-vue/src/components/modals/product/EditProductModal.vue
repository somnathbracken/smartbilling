<template>
  <div class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
    <div class="bg-white w-[90%] max-w-6xl rounded-xl shadow-lg max-h-[95vh] overflow-y-auto p-4">
      <h2 class="text-lg font-semibold mb-4 text-gray-800">Edit Product</h2>
      <form @submit.prevent="update" class="space-y-6">

        <!-- Basic Details -->
        <section>
          <h3 class="text-lg font-semibold border-b pb-1 mb-3">Basic Details</h3>
          <div class="grid grid-cols-2 gap-4">
            <div>
              <label>Product Code</label>
              <input v-model="localProduct.productCode" class="input" type="text" />
            </div>
            <div>
              <label>Product Name</label>
              <input v-model="localProduct.name" class="input" type="text" />
            </div>
          </div>
        </section>

        <!-- Inventory Info -->
        <section>
          <h3 class="text-lg font-semibold border-b pb-1 mb-3">Inventory Info</h3>
          <div class="grid grid-cols-3 gap-4">
            <div>
              <label>Site</label>
              <select v-model="localProduct.siteId" class="input">
                <option v-for="site in sites" :key="site.id" :value="site.id">{{ site.name }}</option>
              </select>
            </div>
            <div>
              <label>Stock Location</label>
              <select v-model="localProduct.stockId" class="input">
                <option v-for="stock in stocks" :key="stock.id" :value="stock.id">{{ stock.name }}</option>
              </select>
            </div>
            <div>
              <label>Warehouse</label>
              <select v-model="localProduct.warehouseId" class="input">
                <option v-for="warehouse in warehouses" :key="warehouse.id" :value="warehouse.id">{{ warehouse.name }}</option>
              </select>
            </div>
          </div>
        </section>

        <!-- Classification -->
        <section>
          <h3 class="text-lg font-semibold border-b pb-1 mb-3">Classification</h3>
          <div class="grid grid-cols-3 gap-4">
            <div>
              <label>Category</label>
              <select v-model="localProduct.categoryId" class="input">
                <option v-for="cat in categories" :key="cat.id" :value="cat.id">{{ cat.name }}</option>
              </select>
            </div>
            <div>
              <label>Group</label>
              <select v-model="localProduct.groupId" class="input">
                <option v-for="item in groups" :key="item.id" :value="item.id">{{ item.name }}</option>
              </select>
            </div>
            <div>
              <label>Brand</label>
              <select v-model="localProduct.brandId" class="input">
                <option v-for="item in brands" :key="item.id" :value="item.id">{{ item.name }}</option>
              </select>
            </div>
            <div>
              <label>Generic Product</label>
              <select v-model="localProduct.genericId" class="input">
                <option v-for="item in generics" :key="item.id" :value="item.id">{{ item.name }}</option>
              </select>
            </div>
            <div>
              <label>UOM</label>
              <select v-model="localProduct.unitOfMeasure" class="input">
                <option v-for="uom in uoms" :key="uom.id" :value="uom.id">{{ uom.name }}</option>
              </select>
            </div>
          </div>
        </section>

        <!-- Description -->
        <section>
          <h3 class="text-lg font-semibold border-b pb-1 mb-3">Description</h3>
          <textarea v-model="localProduct.description" class="input w-full h-24 resize-y" placeholder="Enter product description here..."></textarea>
        </section>

        <!-- Pricing -->
        <section>
          <h3 class="text-lg font-semibold border-b pb-1 mb-3">Pricing & SKU</h3>
          <div class="grid grid-cols-3 gap-4">
            <div><label>Purchase Price</label><input v-model="localProduct.pruchasePrice" class="input" type="number" step="0.01" /></div>
            <div><label>MRP</label><input v-model="localProduct.mrp" class="input" type="number" step="0.01" /></div>
            <div><label>Pack Size</label><input v-model="localProduct.packSize" class="input" type="number" /></div>
            <div><label>Quantity</label><input v-model="localProduct.quantity" class="input" type="number" /></div>
            <div><label>Average Cost</label><input v-model="localProduct.averageCost" class="input" type="number" step="0.01" /></div>
            <div><label>Single Unit Code</label><input v-model="localProduct.singleUnitProductCode" class="input" type="text" /></div>
          </div>
        </section>

        <!-- Tax & Compliance -->
        <section>
          <h3 class="text-lg font-semibold border-b pb-1 mb-3">Tax & Compliance</h3>
          <div class="grid grid-cols-3 gap-4">
            <div><label>HSN Code</label><input v-model="localProduct.hsnCode" class="input" type="text" /></div>
            <div><label>HSN Description</label><input v-model="localProduct.hsnDescription" class="input" type="text" /></div>
            <div><label>Model/Part ID</label><input v-model="localProduct.modelId" class="input" type="text" /></div>
            <div><label>Warranty Terms</label><input v-model="localProduct.warrantyTerms" class="input" type="text" /></div>
            <div>
              <label>Product Lot</label>
              <select v-model="localProduct.productLotId" class="input">
                <option v-for="lot in productLots" :key="lot.id" :value="lot.id">{{ lot.lot_code }} (MFG: {{ lot.dateManufactured }} / EXP: {{ lot.dateExpiry }})</option>
              </select>
            </div>
            <div>
              <label>GST</label>
              <select v-model="localProduct.gstPercentage" class="input">
                <option v-for="gst in gstTaxes" :key="gst.id" :value="gst.id">{{ gst.name }} ({{ gst.percentage }}%)</option>
              </select>
            </div>
          </div>
        </section>

        <!-- Associations -->
        <section>
          <h3 class="text-lg font-semibold border-b pb-1 mb-3">Associations</h3>
          <div class="grid grid-cols-3 gap-4">
            <div>
              <label>Vendor</label>
              <select v-model="localProduct.vendorId" class="input">
                <option disabled value="">Select Vendor</option>
                <option v-for="vendor in vendors" :key="vendor.id" :value="vendor.id">{{ vendor.name }}</option>
              </select>
            </div>
            <div>
              <label>Purchase Order</label>
              <select v-model="localProduct.purchaseOrderId" class="input">
                <option value="">Select PO</option>
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
          </div>
        </section>

        <!-- Flags & Discounts -->
        <section>
          <h3 class="text-lg font-semibold border-b pb-1 mb-3">Discounts & Status</h3>
          <div class="grid grid-cols-3 gap-4 items-center">
            <div>
              <label>Discount (%)</label>
              <input v-model="localProduct.discounts" class="input" type="number" step="0.01" />
            </div>
            <div class="col-span-1 flex items-center space-x-2 mt-6">
              <label for="isActive">Is Active</label>
              <input type="checkbox" v-model="localProduct.isActive" />
            </div>
          </div>
        </section>

        <!-- Actions -->
        <div class="flex justify-end space-x-4 mt-8">
          <button type="submit" class="px-4 py-2 rounded bg-blue-600 text-white hover:bg-blue-700">Update</button>
          <button type="button" class="px-4 py-2 rounded bg-red-500 text-white hover:bg-red-600" @click="$emit('close')">Cancel</button>
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
  @apply w-full text-sm p-2 rounded border border-gray-300 bg-gray-50 focus:outline-none focus:ring-2 focus:ring-blue-300;
}
.label {
  @apply text-xs text-gray-600 font-medium mb-1 block;
}
.section-title {
  @apply col-span-3 text-sm font-semibold text-blue-700 mb-2 mt-4;
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