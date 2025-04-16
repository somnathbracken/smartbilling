<template>
    <div v-if="isOpen" class="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-40">
      <div class="bg-white p-6 rounded-xl shadow-xl w-full max-w-md">
        <div class="flex justify-between items-center mb-4">
          <h2 class="text-xl font-semibold">Add Product Attribute Value</h2>
          <button @click="closeModal" class="text-gray-500 hover:text-red-600 text-lg">&times;</button>
        </div>
  
        <form @submit.prevent="submitForm">
          <div class="space-y-4">
            <div>
              <label class="block text-sm font-medium mb-1">Product Attribute</label>
              <select v-model="product_attribute_value.product_attribute_id" class="w-full border px-3 py-2 rounded">
                <option v-for="productAttribute in productAttributes" :key="productAttribute.id" :value="productAttribute.id">
                  {{ productAttribute.attribute_name }}
                </option>
              </select>
            </div>
  
            <div>
              <label class="block text-sm font-medium mb-1">Value</label>
              <input v-model="product_attribute_value.value" type="text" class="w-full border px-3 py-2 rounded" placeholder="Enter value" />
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
    productAttributes: Array,
  })
  
  const emit = defineEmits(['close', 'save'])
  
  const product_attribute_value = ref({
    product_attribute_id: '',
    value: '',
  })
  
  const closeModal = () => {
    emit('close')
  }
  
  const submitForm = () => {
    if (product_attribute_value.value.product_attribute_id && product_attribute_value.value.value) {
      emit('save', { ...product_attribute_value.value })
      product_attribute_value.value = { product_attribute_id: '', value: '' }
      closeModal()
    } else {
      alert('Please select a product attribute and provide a value.')
    }
  }
  </script>
  