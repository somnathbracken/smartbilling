<template>
    <div v-if="isOpen" class="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-40">
      <div class="bg-white p-6 rounded-xl shadow-xl w-full max-w-md">
        <div class="flex justify-between items-center mb-4">
          <h2 class="text-xl font-semibold">Add Purchase Order Header</h2>
          <button @click="closeModal" class="text-gray-500 hover:text-red-600 text-lg">&times;</button>
        </div>
  
        <form @submit.prevent="submitForm">
          <div class="space-y-4">
            <!-- Supplier ID -->
            <div>
              <label class="block text-sm font-medium mb-1">Supplier</label>
              <select v-model="purchaseOrder.supplier_id" class="w-full border px-3 py-2 rounded">
                <option v-for="supplier in suppliers" :key="supplier.id" :value="supplier.id">
                  {{ supplier.supplier_name }}
                </option>
              </select>
            </div>
  
            <!-- Purchase Date -->
            <div>
              <label class="block text-sm font-medium mb-1">Purchase Date</label>
              <input v-model="purchaseOrder.purchase_date" type="date" class="w-full border px-3 py-2 rounded" />
            </div>
  
            <!-- Total Amount -->
            <div>
              <label class="block text-sm font-medium mb-1">Total Amount</label>
              <input v-model="purchaseOrder.total_amount" type="number" min="0" step="0.01" class="w-full border px-3 py-2 rounded" placeholder="Enter total amount" />
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
    suppliers: Array,
  })
  
  const emit = defineEmits(['close', 'save'])
  
  const purchaseOrder = ref({
    supplier_id: null,
    purchase_date: '',
    total_amount: 0,
  })
  
  const closeModal = () => {
    emit('close')
  }
  
  const submitForm = () => {
    if (purchaseOrder.value.supplier_id && purchaseOrder.value.purchase_date && purchaseOrder.value.total_amount > 0) {
      emit('save', { ...purchaseOrder.value })
      purchaseOrder.value = { supplier_id: null, purchase_date: '', total_amount: 0 }
      closeModal()
    } else {
      alert('Please fill in all fields and ensure total amount is greater than zero.')
    }
  }
  </script>
  