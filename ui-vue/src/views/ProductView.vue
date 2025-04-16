<template>
  <div class="p-6">
    <h2 class="text-2xl font-bold mb-4">Product Master</h2>
    <form class="grid grid-cols-4 gap-4">
      <div>
        <label>Product Code</label>
        <input v-model="product.product_code" class="input" type="text" />
      </div>
      <div>
        <label>Product Name</label>
        <input v-model="product.product_name" class="input" type="text" />
      </div>
      <div class="col-span-1">
        <label class="block font-medium text-gray-700">Category</label>
        <div class="flex items-center space-x-2">
          <select v-model="product.category_id" class="border p-2 rounded w-full">
            <option v-for="cat in categories" :key="cat.id" :value="cat.id">{{ cat.name }}</option>
          </select>
          <button type="button" class="btn" @click="showCategoryModal = true">+</button>
        </div>
      </div>
      <div>
        <label>Group</label>
        <div class="flex items-center space-x-2">
          <select v-model="product.group_id" class="input">
            <option disabled value="">Select Group</option>
            <option v-for="item in groups" :key="item.id" :value="item.id">{{ item.name }}</option>
          </select>
          <button type="button" class="btn" @click="showGroupModal = true">+</button>
        </div>
      </div>
      <div>
        <label>Brand</label>
        <div class="flex items-center space-x-2">
          <select v-model="product.brand_id" class="input">
            <option disabled value="">Select Brand</option>
            <option v-for="item in brands" :key="item.id" :value="item.id">{{ item.name }}</option>
          </select>
          <button type="button" class="btn" @click="showBrandModal = true">+</button>
        </div>
      </div>
      <div>
        <label>Generic Product</label>
        <div class="flex items-center space-x-2">
          <select v-model="product.generic_id" class="input">
            <option disabled value="">Select Generic</option>
            <option v-for="item in generics" :key="item.id" :value="item.id">{{ item.name }}</option>
          </select>
          <button type="button" class="btn" @click="showGenericModal = true">+</button>
        </div>
      </div>
      <div class="col-span-4">
        <label>Product Description</label>
        <textarea v-model="product.product_description" class="input"></textarea>
      </div>
      <div>
        <label>Price</label>
        <input v-model="product.product_price" class="input" type="number" step="0.01" />
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
      <div>
        <label>HSN Code</label>
        <input v-model="product.hsn_code" class="input" type="text" />
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
        <label>Lot Information</label>
        <input v-model="product.lot_information" class="input" type="text" />
      </div>
      <div>
        <label>Supplier</label>
        <select v-model="product.supplierId" class="input">
          <option value="">Select Supplier</option>
          <option v-for="supplier in suppliers" :key="supplier.id" :value="supplier.id">{{ supplier.name }}</option>
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
      <div class="flex items-center">
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
      </div>
      <div class="flex items-center">
        <label class="mr-2">Is Active</label>
        <input type="checkbox" v-model="product.is_active" />
      </div>
      <div class="flex items-center">
        <label class="mr-2">Deleted</label>
        <input type="checkbox" v-model="product.deleted" />
      </div>
      <div class="col-span-4">
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
  </div>
</template>

<script setup>
import { ref } from 'vue'
import CategoryModal from '../components/modals/product/CategoryModal.vue'
import GroupModal from '../components/modals/product/GroupModal.vue'
import BrandModal from '../components/modals/product/BrandModal.vue'
import GenericProductModal from '../components/modals/product/GenericProductModal.vue'

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
const productAttributes = ref([])

const showCategoryModal = ref(false)
const showGroupModal = ref(false)
const showBrandModal = ref(false)
const showGenericModal = ref(false)

function handleBrandSaved(brand) {
  showBrandModal.value = false
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
</script>

<style scoped>
.input {
  @apply border p-2 rounded w-full;
}
.btn {
  @apply mt-1 ml-2 bg-green-500 text-white px-2 py-1 rounded;
}
</style>
