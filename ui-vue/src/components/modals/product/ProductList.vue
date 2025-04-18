<template>
  <div class="p-6 bg-white rounded-xl shadow-md">
    <h2 class="text-2xl font-semibold text-gray-800 mb-6">Product List</h2>

    <div class="overflow-x-auto">
      <table class="min-w-full border border-gray-300 border-collapse text-sm">
        <thead class="bg-gray-100">
          <tr class="divide-x divide-gray-300">
          <th class="px-4 py-2 border border-gray-300 text-left">Name</th>
          <th class="px-4 py-2 border border-gray-300 text-left">Product Code</th>
          <th class="px-4 py-2 border border-gray-300 text-right">MRP</th>
          <th class="px-4 py-2 border border-gray-300 text-right">Qty</th>
          <th class="px-4 py-2 border border-gray-300 text-center">Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(prod, index) in products.filter(p => p && (p.deleted === false || p.deleted === 'false'))" :key="index" class="text-left">
              <td class="px-4 py-2 border border-gray-200 font-medium text-left">{{ prod.name }}</td>
              <td class="px-4 py-2 border border-gray-200 font-medium text-left">{{ prod.productCode }}</td>
              <td class="px-4 py-2 border border-gray-200 text-right">{{ parseFloat(prod.mrp).toFixed(2) }}</td>
              <td class="px-4 py-2 border border-gray-200 text-right">{{ prod.quantity }}</td>
              <td class="px-4 py-2 border border-gray-200 text-center space-x-2">
                <button @click="editProduct(prod)" class="btn-primary-sm">Edit</button>
                <button @click="deleteProduct(prod.id)" class="btn-danger-sm">Delete</button>
              </td>
            </tr>
      </tbody>
    </table>
    </div>
  </div>
</template>
  
  <script setup>
  import { ref, onMounted, computed } from 'vue'
  import { getAllProducts, deleteProductById } from '../../../services/ProductService';
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
    
    const deleteProduct = async (id) => {
      await deleteProductById(id)
      await loadProducts()
    }
    onMounted(loadProducts)
  </script>
  
  <style scoped>
/* Add these to your tailwind base layer or a global style file */
.input-style {
  @apply border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400 text-sm;
}
.btn-primary {
  @apply bg-blue-600 text-white px-4 py-2 rounded-md hover:bg-blue-700 text-sm font-medium;
}
.btn-secondary {
  @apply bg-gray-200 text-gray-700 px-4 py-2 rounded-md hover:bg-gray-300 text-sm font-medium;
}
.btn-primary-sm {
  @apply bg-blue-500 text-white px-3 py-1 rounded hover:bg-blue-600 text-xs;
}
.btn-danger-sm {
  @apply bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600 text-xs;
}
.btn-success {
  @apply bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600 text-sm font-medium;
}
.btn-warning {
  @apply bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600 text-sm font-medium;
}

  </style>
  