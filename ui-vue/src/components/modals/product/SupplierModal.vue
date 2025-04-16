<template>
    <div v-if="isOpen" class="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-40">
      <div class="bg-white p-6 rounded-xl shadow-xl w-full max-w-md">
        <div class="flex justify-between items-center mb-4">
          <h2 class="text-xl font-semibold">Add Supplier</h2>
          <button @click="closeModal" class="text-gray-500 hover:text-red-600 text-lg">&times;</button>
        </div>
  
        <form @submit.prevent="submitForm">
          <div class="space-y-4">
            <!-- Supplier Code -->
            <div>
              <label class="block text-sm font-medium mb-1">Supplier Code</label>
              <input
                v-model="supplier.supplier_code"
                type="text"
                class="w-full border px-3 py-2 rounded"
                placeholder="Enter supplier code"
              />
            </div>
  
            <!-- Supplier Name -->
            <div>
              <label class="block text-sm font-medium mb-1">Supplier Name</label>
              <input
                v-model="supplier.supplier_name"
                type="text"
                class="w-full border px-3 py-2 rounded"
                placeholder="Enter supplier name"
              />
            </div>
  
            <!-- Supplier Type -->
            <div>
              <label class="block text-sm font-medium mb-1">Supplier Type</label>
              <select
                v-model="supplier.supplier_type"
                class="w-full border px-3 py-2 rounded"
              >
                <option value="" disabled>Select type</option>
                <option value="Manufacturer">Manufacturer</option>
                <option value="Distributor">Distributor</option>
                <option value="Retailer">Retailer</option>
                <option value="Wholesaler">Wholesaler</option>
              </select>
            </div>
          </div>
  
          <div class="flex justify-end gap-2 mt-6">
            <button
              type="button"
              @click="closeModal"
              class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-semibold py-2 px-4 rounded"
            >
              Cancel
            </button>
            <button
              type="submit"
              class="bg-green-600 hover:bg-green-700 text-white font-semibold py-2 px-4 rounded"
            >
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
    isOpen: Boolean
  })
  
  const emit = defineEmits(['close', 'save'])
  
  const supplier = ref({
    supplier_code: '',
    supplier_name: '',
    supplier_type: ''
  })
  
  const closeModal = () => {
    emit('close')
  }
  
  const submitForm = () => {
    if (supplier.value.supplier_code && supplier.value.supplier_name && supplier.value.supplier_type) {
      emit('save', { ...supplier.value })
      supplier.value = { supplier_code: '', supplier_name: '', supplier_type: '' }
      closeModal()
    } else {
      alert('Please fill in all fields.')
    }
  }
  </script>
  