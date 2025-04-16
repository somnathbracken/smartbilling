<template>
    <div v-if="isOpen" class="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-40">
      <div class="bg-white p-6 rounded-xl shadow-xl w-full max-w-md">
        <div class="flex justify-between items-center mb-4">
          <h2 class="text-xl font-semibold">Add Product Price Record</h2>
          <button @click="closeModal" class="text-gray-500 hover:text-red-600 text-lg">&times;</button>
        </div>
  
        <form @submit.prevent="submitForm">
          <div class="space-y-4">
            <!-- Product ID (Assumed to be passed or hidden) -->
            <div>
              <label class="block text-sm font-medium mb-1">Product ID</label>
              <input
                v-model="priceRecord.product_id"
                type="text"
                class="w-full border px-3 py-2 rounded"
                placeholder="Enter product ID"
                :readonly="!!productId"
              />
            </div>
  
            <!-- From Date -->
            <div>
              <label class="block text-sm font-medium mb-1">From Date</label>
              <input
                v-model="priceRecord.from_date"
                type="date"
                class="w-full border px-3 py-2 rounded"
              />
            </div>
  
            <!-- Product Price -->
            <div>
              <label class="block text-sm font-medium mb-1">Product Price</label>
              <input
                v-model="priceRecord.product_price"
                type="number"
                step="0.01"
                class="w-full border px-3 py-2 rounded"
                placeholder="Enter product price"
              />
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
              class="bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2 px-4 rounded"
            >
              Save
            </button>
          </div>
        </form>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, watch } from 'vue'
  
  const props = defineProps({
    isOpen: Boolean,
    productId: String
  })
  
  const emit = defineEmits(['close', 'save'])
  
  const priceRecord = ref({
    product_id: '',
    from_date: '',
    product_price: ''
  })
  
  watch(() => props.productId, (newVal) => {
    if (newVal) {
      priceRecord.value.product_id = newVal
    }
  })
  
  const closeModal = () => {
    emit('close')
  }
  
  const submitForm = () => {
    if (priceRecord.value.product_id && priceRecord.value.from_date && priceRecord.value.product_price) {
      emit('save', { ...priceRecord.value })
      priceRecord.value = { product_id: '', from_date: '', product_price: '' }
      closeModal()
    } else {
      alert('Please complete all fields.')
    }
  }
  </script>
  