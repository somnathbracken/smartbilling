<template>
    
    <!-- Product List -->
    <div class="bg-white shadow rounded-lg p-4">
      <h3 class="text-lg font-semibold mb-4">Product List</h3>
      <table class="min-w-full table-auto border">
        <thead class="bg-gray-100">
          <tr>
            <th class="px-4 py-2 border">Name</th>
            <th class="px-4 py-2 border">SKU</th>
            <th class="px-4 py-2 border">Price</th>
            <th class="px-4 py-2 border">Qty</th>
            <th class="px-4 py-2 border">Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(prod, index) in products" :key="index" class="text-center">
            <td class="border px-4 py-2">{{ prod.name }}</td>
            <td class="border px-4 py-2">{{ prod.sku }}</td>
            <td class="border px-4 py-2">{{ prod.unit_price }}</td>
            <td class="border px-4 py-2">{{ prod.quantity }}</td>
            <td class="border px-4 py-2">
              <button @click="editProduct(prod)" class="text-blue-600 hover:underline mr-2">Edit</button>
              <button @click="deleteProduct(prod.id)" class="text-red-600 hover:underline">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue'
  import { getAllProducts } from '../../../services/ProductService';
  //import axios from 'axios'
  defineProps(['products'])
  const emit = defineEmits(['edit-product', 'delete-product'])

  const products = ref([])
    const loadProducts = async () => {
      try {
        const res = await getAllProducts()
        // Make sure it's an array
        products.value = Array.isArray(res.data) ? res.data : []
      } catch (err) {
        console.error('Failed to load products:', err)
        products.value = []
      }
    }
    const editProduct = (product) => {
      form.value = { ...product }
      selectedProduct.value = product
      showForm.value = true
    }
    
    // const deleteProduct = async (id) => {
    //   await deleteProductById(id)
    //   await loadProducts()
    // }
    onMounted(loadProducts)
  </script>
  
  <style scoped>
  .btn {
    @apply bg-green-500 text-white px-3 py-1 rounded hover:bg-green-600;
  }
  .btn-red {
    @apply bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600;
  }
  </style>
  