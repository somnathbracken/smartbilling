<template>
  <div :key="componentKey" class="p-6">
    <!-- Toggle Button -->
    <div class="mb-4">
      <button
        @click="toggleForm"
        class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
      >
        {{ showForm ? 'Hide Product Entry' : 'Add Product' }}
      </button>
    </div>

    <!-- Form Component -->
    <ProductForm
      v-if="showForm"
      :product="product"
      :selectedProduct="selectedProduct"
      :showForm="showForm"
      :sites="sites"
      :stocks="stocks"
      :warehouses="warehouses"
      :categories="categories"
      :groups="groups"
      :brands="brands"
      :generics="generics"
      :uoms="uoms"
      :productLots="productLots"
      :gstTaxes="gstTaxes"
      :vendors="vendors"
      :purchaseOrders="purchaseOrders"
      :productAttributes="productAttributes"
      @save-product="saveProduct"
    />

    <!-- Product List Component -->
    <ProductList
      :products="productList"
      @edit-product="editProduct"
      @delete-product="deleteProduct"
    />
  </div>
</template>

<script setup>
import { ref } from 'vue'
import ProductForm from '../components/modals/product/ProductForm.vue'
import ProductList from '../components/modals/product/ProductList.vue'

// Props and state
const showForm = ref(false)
const componentKey = ref(0)
const product = ref({}) // Init with default structure
const selectedProduct = ref(null)
const productList = ref([]) // Mock or fetched list

// Dummy data for props
const sites = ref([]), stocks = ref([]), warehouses = ref([]), categories = ref([]),
  groups = ref([]), brands = ref([]), generics = ref([]), uoms = ref([]),
  productLots = ref([]), gstTaxes = ref([]), vendors = ref([]),
  purchaseOrders = ref([]), productAttributes = ref([])

const toggleForm = () => {
  showForm.value = !showForm.value
}

const saveProduct = () => {
  if (selectedProduct.value) {
    // update product
    const index = productList.value.findIndex(p => p.id === selectedProduct.value.id)
    if (index !== -1) productList.value[index] = { ...product.value }
  } else {
    // new product
    product.value.id = Date.now()
    productList.value.push({ ...product.value })
  }
  resetForm()
}

const editProduct = (prod) => {
  product.value = { ...prod }
  selectedProduct.value = prod
  showForm.value = true
}

const deleteProduct = (id) => {
  productList.value = productList.value.filter(p => p.id !== id)
}

const resetForm = () => {
  product.value = {}
  selectedProduct.value = null
  showForm.value = false
}
</script>
