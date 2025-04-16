<template>
    <div v-if="isOpen" class="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-40">
      <div class="bg-white p-6 rounded-xl shadow-xl w-full max-w-md">
        <div class="flex justify-between items-center mb-4">
          <h2 class="text-xl font-semibold">Add Stock</h2>
          <button @click="closeModal" class="text-gray-500 hover:text-red-600 text-lg">&times;</button>
        </div>
  
        <form @submit.prevent="submitForm">
          <div class="space-y-4">
            <!-- Product ID -->
            <div>
              <label class="block text-sm font-medium mb-1">Product</label>
              <select v-model="stock.product_id" class="w-full border px-3 py-2 rounded">
                <option v-for="product in products" :key="product.id" :value="product.id">
                  {{ product.product_name }}
                </option>
              </select>
            </div>
  
            <!-- Warehouse ID -->
            <div>
              <label class="block text-sm font-medium mb-1">Warehouse</label>
              <select v-model="stock.warehouse_id" class="w-full border px-3 py-2 rounded">
                <option v-for="warehouse in warehouses" :key="warehouse.id" :value="warehouse.id">
                  {{ warehouse.warehouse_name }}
                </option>
              </select>
            </div>
  
            <!-- Quantity in Hand -->
            <div>
              <label class="block text-sm font-medium mb-1">Quantity in Hand</label>
              <input v-model="stock.quantity" type="number" min="0" class="w-full border px-3 py-2 rounded" placeholder="Enter quantity" />
            </div>
  
            <!-- Product Attribute Value ID -->
            <div>
              <label class="block text-sm font-medium mb-1">Product Attribute</label>
              <select v-model="stock.product_attribute_value_id" class="w-full border px-3 py-2 rounded">
                <option v-for="attribute in productAttributes" :key="attribute.id" :value="attribute.id">
                  {{ attribute.value }}
                </option>
              </select>
            </div>
  
            <!-- Product Price -->
            <div>
              <label class="block text-sm font-medium mb-1">Product Price</label>
              <input v-model="stock.product_price" type="number" min="0" step="0.01" class="w-full border px-3 py-2 rounded" placeholder="Enter price" />
            </div>
          </div>
  
          <div class="flex justify-end gap-2 mt-6">
            <button type="button" @click="closeModal" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-semibold py-2 px-4 rounded">
              Cancel
            </button>
            <button type="submit" class="bg-green-600 hover:bg-green-700 text-white font-semibold py-2 px-4 rounded">
              Save
            </button>
          </div>
        </form>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  
  const props = defineProps({
    isOpen: Boolean,
    products: Array,
    warehouses: Array,
    productAttributes: Array,
  })
  
  const emit = defineEmits(['close', 'save'])
  
  const stock = ref({
    product_id: null,
    warehouse_id: null,
    quantity: 0,
    product_attribute_value_id: null,
    product_price: 0,
  })
  
  const closeModal = () => {
    emit('close')
  }
  
  const submitForm = () => {
    if (stock.value.product_id && stock.value.warehouse_id && stock.value.quantity > 0) {
      emit('save', { ...stock.value })
      stock.value = { product_id: null, warehouse_id: null, quantity: 0, product_attribute_value_id: null, product_price: 0 }
      closeModal()
    } else {
      alert('Please fill in all fields and ensure quantity is greater than zero.')
    }
  }
  </script>
  