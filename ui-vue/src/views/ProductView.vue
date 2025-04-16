<template>
  <div class="p-6">
    <h2 class="text-2xl font-bold mb-4">Product Master</h2>
    <form class="grid grid-cols-4 gap-4">

<!-- Basic Details -->
<div class="col-span-4 text-lg font-semibold mt-4 mb-2">Basic Details</div>
<div>
  <label>Product Code</label>
  <input v-model="product.product_code" class="input" type="text" />
</div>
<div>
  <label>Product Name</label>
  <input v-model="product.product_name" class="input" type="text" />
</div>

<!-- Inventory Details -->
<div class="col-span-4 text-lg font-semibold mt-4 mb-2">Inventory Details</div>
<div>
  <label>Site</label>
  <div class="flex space-x-2 items-center">
    <select v-model="product.site_id" class="input">
      <option v-for="site in sites" :key="site.id" :value="site.id">{{ site.name }}</option>
    </select>
    <button type="button" class="btn" @click="showSiteModal = true">+</button>
  </div>
</div>
<div>
  <label>Stock</label>
  <div class="flex space-x-2 items-center">
    <select v-model="product.stock_id" class="input">
      <option v-for="stock in stocks" :key="stock.id" :value="stock.id">{{ stock.name }}</option>
    </select>
    <button type="button" class="btn" @click="showStockModal = true">+</button>
  </div>
</div>
<div>
  <label>Warehouse</label>
  <div class="flex space-x-2 items-center">
    <select v-model="product.warehouse_id" class="input">
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
    <select v-model="product.category_id" class="input">
      <option v-for="cat in categories" :key="cat.id" :value="cat.id">{{ cat.name }}</option>
    </select>
    <button type="button" class="btn" @click="showCategoryModal = true">+</button>
  </div>
</div>
<div>
  <label>Group</label>
  <div class="flex items-center space-x-2">
    <select v-model="product.group_id" class="input">
      <option v-for="item in groups" :key="item.id" :value="item.id">{{ item.name }}</option>
    </select>
    <button type="button" class="btn" @click="showGroupModal = true">+</button>
  </div>
</div>
<div>
  <label>Brand</label>
  <div class="flex items-center space-x-2">
    <select v-model="product.brand_id" class="input">
      <option v-for="item in brands" :key="item.id" :value="item.id">{{ item.name }}</option>
    </select>
    <button type="button" class="btn" @click="showBrandModal = true">+</button>
  </div>
</div>
<div>
  <label>Generic Product</label>
  <div class="flex items-center space-x-2">
    <select v-model="product.generic_id" class="input">
      <option v-for="item in generics" :key="item.id" :value="item.id">{{ item.name }}</option>
    </select>
    <button type="button" class="btn" @click="showGenericModal = true">+</button>
  </div>
</div>
<div>
  <label>UOM</label>
  <div class="flex space-x-2 items-center">
    <select v-model="product.uom_id" class="input">
      <option v-for="uom in uoms" :key="uom.id" :value="uom.id">{{ uom.name }}</option>
    </select>
    <button type="button" class="btn" @click="showUomModal = true">+</button>
  </div>
  </div>
<!-- Descriptions -->
<div class="col-span-4 text-lg font-semibold mt-4 mb-2">Descriptions</div>
<div class="col-span-4">
  <label>Product Description</label>
  <textarea v-model="product.product_description" class="input"></textarea>
</div>

<!-- Pricing & SKU -->
<div class="col-span-4 text-lg font-semibold mt-4 mb-2">Pricing & SKU</div>
<div>
  <label>Price</label>
  <input v-model="product.product_purchase_price" class="input" type="number" step="0.01" />
</div>
<div>
  <label>MRP</label>
  <input v-model="product.product_mrp" class="input" type="number" step="0.01" />
</div>
<div>
  <label>Pack Size</label>
  <input v-model="product.pack_size" class="input" type="number" />
</div>
<div>
  <label>Average Cost</label>
  <input v-model="product.average_cost" class="input" type="number" step="0.01" />
</div>
<div>
  <label>Single Unit Product Code</label>
  <input v-model="product.single_unit_product_code" class="input" type="text" />
</div>

<!-- Tax & Compliance -->
<div class="col-span-4 text-lg font-semibold mt-4 mb-2">Tax & Compliance</div>
<div>
  <label>HSN Code</label>
  <input v-model="product.hsn_code" class="input" type="text" />
</div>
<div>
  <label>HSN Description</label>
  <input v-model="product.hsn_description" class="input" type="text" />
</div>
<div>
  <label>Model/Part ID</label>
  <input v-model="product.model_id" class="input" type="text" />
</div>
<div>
  <label>Warranty Terms</label>
  <input v-model="product.warranty_terms" class="input" type="text" />
</div>
<div>
  <label>Product Lot</label>
  <div class="flex items-center space-x-2">
    <select v-model="product.lot_id" class="input">
      <option v-for="lot in productLots" :key="lot.id" :value="lot.id">
        {{ lot.lot_code }} (MFG: {{ lot.date_manufactured }} / EXP: {{ lot.date_expiry }})
      </option>
    </select>
    <button type="button" class="btn" @click="showLotModal = true">+</button>
  </div>
</div>

<div>
  <label>GST Tax</label>
  <div class="flex items-center space-x-2">
    <select v-model="product.gst_id" class="input">
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
  <select v-model="selectedVendor" class="form-select border p-2 rounded w-full">
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
  <input v-model="product.product_discounts" class="input" type="number" step="0.01" />
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
  <input type="checkbox" v-model="product.is_active" />
</div>
<div class="flex items-center">
  <label class="mr-2">Deleted</label>
  <input type="checkbox" v-model="product.deleted" />
</div>

<!-- Actions -->
<div class="col-span-4 mt-6">
  <button class="btn bg-blue-600 text-white px-4 py-2 rounded" @click.prevent="saveProduct">
    Save Product
  </button>
</div>

</form>


    <!-- Modals -->
    <GenericProductModal :isOpen="showGenericModal" @close="showGenericModal = false" @save="handleGenericSaved" />
    <CategoryModal :show="showCategoryModal" @close="showCategoryModal = false" @saved="handleCategorySaved" />
    <GroupModal :show="showGroupModal" @close="showGroupModal = false" @saved="handleGroupSaved" />
    <BrandModal :show="showBrandModal" @close="showBrandModal = false" @saved="handleBrandSaved" />
    <GstTaxModal :show="showGstModal" @close="showGstModal = false" @saved="handleGSTTaxSaved"/>
    <ProductLotModal :show="showLotModal" :productAttributes="productAttributes" @close="showLotModal = false" @saved="handleLotSaved"/>
    <SiteModal
  :show="showSiteModal"
  @close="showSiteModal = false"
  @added="site => {
    sites.value.push(site)
    product.site_id = site.id
  }"
/>

<StockModal
  :show="showStockModal"
  @close="showStockModal = false"
  @added="stock => {
    stocks.value.push(stock)
    product.stock_id = stock.id
  }"
/>

<WarehouseModal
  :show="showWarehouseModal"
  @close="showWarehouseModal = false"
  @added="warehouse => {
    warehouses.value.push(warehouse)
    product.warehouse_id = warehouse.id
  }"
/>

<UomModal
  :show="showUomModal"
  @close="showUomModal = false"
  @added="uom => {
    uoms.value.push(uom)
    product.default_uom = uom.id
  }"
/>

  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

import CategoryModal from '../components/modals/product/CategoryModal.vue'
import GroupModal from '../components/modals/product/GroupModal.vue'
import BrandModal from '../components/modals/product/BrandModal.vue'
import GenericProductModal from '../components/modals/product/GenericProductModal.vue'
import GstTaxModal from '../components/modals/product/GstTaxModal.vue'

import ProductLotModal from '../components/modals/product/ProductLotModal.vue'
import SiteModal from '../components/modals/product/SiteModal.vue'
import StockModal from '../components/modals/product/StockModal.vue'
import WarehouseModal from '../components/modals/product/WarehouseModal.vue'
import UomModal from '../components/modals/product/UomModal.vue'

const showSiteModal = ref(false)
const showStockModal = ref(false)
const showWarehouseModal = ref(false)
const showUomModal = ref(false)

const sites = ref([{ id: 1, name: 'Site A' }])
const stocks = ref([{ id: 1, name: 'Stock Room 1' }])
const warehouses = ref([{ id: 1, name: 'Warehouse Alpha' }])
const uoms = ref([{ id: 1, name: 'Piece' }])

const showLotModal = ref(false)
const productLots = ref([
  { id: 1, lot_code: 'LOT001', date_manufactured: '2024-01-01', date_expiry: '2025-01-01', product_attribute_value_id: '' }
])

const productAttributes = ref([
  { id: 'a1', name: 'Color: Red' },
  { id: 'a2', name: 'Size: Medium' }
])

const showGstModal = ref(false)
const gstTaxes = ref([{ id: 1, name: 'GST 18%', percentage: 18 }]) // sample list

const product = ref({
  product_code: '',
  product_name: '',
  category_id: '',
  group_id: '',
  brand_id: '',
  generic_id: '',
  product_description: '',
  product_price: 0,
  pack_size: 0,
  average_cost: 0,
  single_unit_product_code: '',
  hsn_code: '',
  model_id: '',
  warranty_terms: '',
  lot_information: '',
  supplierId: '',
  purchaseOrderId: '',
  productAttributeId: '',
  has_attributes: false,
  has_lots: false,
  has_instances: false,
  is_active: true,
  deleted: false
})

const categories = ref([{ id: 1, name: 'Electronics' }])
const groups = ref([{ id: 1, name: 'Group A' }])
const brands = ref([{ id: 1, name: 'Brand X' }])
const generics = ref([{ id: 1, name: 'Generic Y' }])
const suppliers = ref([])
const purchaseOrders = ref([])

const showCategoryModal = ref(false)
const showGroupModal = ref(false)
const showBrandModal = ref(false)
const showGenericModal = ref(false)

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
function saveProduct() {
  console.log('Saving product:', product.value)
  alert('Product saved!')
}

// load dropdowns

const vendors = ref([])

onMounted(async () => {
  try {
    const response = await axios.get('http://localhost:8081/api/vendors/listAll')
    vendors.value = response.data
  } catch (error) {
    console.error('Error fetching vendors:', error)
  }
})
</script>

<style scoped>
.input {
  @apply border p-2 rounded w-full;
}
.btn {
  @apply mt-1 ml-2 bg-green-500 text-white px-2 py-1 rounded;
}
</style>
