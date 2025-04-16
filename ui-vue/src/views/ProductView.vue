<template>
  <div class="p-4">
    <h1 class="text-xl font-bold mb-4">Product Management</h1>
    <ProductForm @refresh="fetchProducts" />
    <ProductGrid :products="products" />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import ProductForm from '../components/ProductForm.vue'
import ProductGrid from '../components/ProductGrid.vue'
import { fetchAllProducts } from '../services/productService'

const products = ref([])
const fetchProducts = async () => {
  products.value = await fetchAllProducts()
}
onMounted(fetchProducts)
</script>
