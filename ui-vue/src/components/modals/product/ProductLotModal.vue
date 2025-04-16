<template>
    <div v-if="isOpen" class="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-40">
      <div class="bg-white p-6 rounded-xl shadow-xl w-full max-w-md">
        <div class="flex justify-between items-center mb-4">
          <h2 class="text-xl font-semibold">Add Product Lot</h2>
          <button @click="closeModal" class="text-gray-500 hover:text-red-600 text-lg">&times;</button>
        </div>
  
        <form @submit.prevent="submitForm">
          <div class="space-y-4">
            <div>
              <label class="block text-sm font-medium mb-1">Lot Code / Batch Code</label>
              <input v-model="product_lot.lot_code" type="text" class="w-full border px-3 py-2 rounded" placeholder="Enter lot code" />
            </div>
  
            <div>
              <label class="block text-sm font-medium mb-1">Manufactured Date</label>
              <input v-model="product_lot.date_manufactured" type="date" class="w-full border px-3 py-2 rounded" />
            </div>
  
            <div>
              <label class="block text-sm font-medium mb-1">Expiry Date</label>
              <input v-model="product_lot.date_expiry" type="date" class="w-full border px-3 py-2 rounded" />
            </div>
  
            <div>
              <label class="block text-sm font-medium mb-1">Product Attribute Value</label>
              <select v-model="product_lot.product_attribute_value_id" class="w-full border px-3 py-2 rounded">
                <option v-for="attributeValue in attributeValues" :key="attributeValue.id" :value="attributeValue.id">
                  {{ attributeValue.value }}
                </option>
              </select>
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
    attributeValues: Array,
  })
  
  const emit = defineEmits(['close', 'save'])
  
  const product_lot = ref({
    lot_code: '',
    date_manufactured: '',
    date_expiry: '',
    product_attribute_value_id: '',
  })
  
  const closeModal = () => {
    emit('close')
  }
  
  const submitForm = () => {
    if (product_lot.value.lot_code && product_lot.value.date_manufactured && product_lot.value.date_expiry) {
      emit('save', { ...product_lot.value })
      product_lot.value = { lot_code: '', date_manufactured: '', date_expiry: '', product_attribute_value_id: '' }
      closeModal()
    } else {
      alert('Please fill in all required fields.')
    }
  }
  </script>
  