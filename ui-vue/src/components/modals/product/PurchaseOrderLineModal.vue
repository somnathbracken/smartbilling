<template>
    <div v-if="isOpen" class="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-40">
      <div class="bg-white p-6 rounded-xl shadow-xl w-full max-w-md">
        <div class="flex justify-between items-center mb-4">
          <h2 class="text-xl font-semibold">Add Purchase Order Line</h2>
          <button @click="closeModal" class="text-gray-500 hover:text-red-600 text-lg">&times;</button>
        </div>
  
        <form @submit.prevent="submitForm">
          <div class="space-y-4">
            <!-- PO ID -->
            <div>
              <label class="block text-sm font-medium mb-1">Purchase Order</label>
              <select v-model="purchaseOrderLine.po_id" class="w-full border px-3 py-2 rounded">
                <option v-for="po in purchaseOrders" :key="po.id" :value="po.id">
                  {{ po.id }} - {{ po.supplier_name }}
                </option>
              </select>
            </div>
  
            <!-- Product ID -->
            <div>
              <label class="block text-sm font-medium mb-1">Product</label>
              <select v-model="purchaseOrderLine.product_id" class="w-full border px-3 py-2 rounded">
                <option v-for="product in products" :key="product.id" :value="product.id">
                  {{ product.product_name }} - {{ product.product_code }}
                </option>
              </select>
            </div>
  
            <!-- Unit Price -->
            <div>
              <label class="block text-sm font-medium mb-1">Unit Price</label>
              <input v-model="purchaseOrderLine.unit_price" type="number" min="0" step="0.01" class="w-full border px-3 py-2 rounded" placeholder="Enter unit price" />
            </div>
  
            <!-- Quantity -->
            <div>
              <label class="block text-sm font-medium mb-1">Quantity</label>
              <input v-model="purchaseOrderLine.quantity" type="number" min="1" class="w-full border px-3 py-2 rounded" placeholder="Enter quantity" />
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
    purchaseOrders: Array,
    products: Array,
  })
  
  const emit = defineEmits(['close', 'save'])
  
  const purchaseOrderLine = ref({
    po_id: null,
    product_id: null,
    unit_price: 0,
    quantity: 1,
  })
  
  const closeModal = () => {
    emit('close')
  }
  
  const submitForm = () => {
    if (
      purchaseOrderLine.value.po_id &&
      purchaseOrderLine.value.product_id &&
      purchaseOrderLine.value.unit_price > 0 &&
      purchaseOrderLine.value.quantity > 0
    ) {
      emit('save', { ...purchaseOrderLine.value })
      purchaseOrderLine.value = { po_id: null, product_id: null, unit_price: 0, quantity: 1 }
      closeModal()
    } else {
      alert('Please fill in all fields and ensure unit price and quantity are greater than zero.')
    }
  }
  </script>
  