<template>
    <div v-if="isOpen" class="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-40">
      <div class="bg-white p-6 rounded-xl shadow-xl w-full max-w-md">
        <div class="flex justify-between items-center mb-4">
          <h2 class="text-xl font-semibold">Add Product Instance</h2>
          <button @click="closeModal" class="text-gray-500 hover:text-red-600 text-lg">&times;</button>
        </div>
  
        <form @submit.prevent="submitForm">
          <div class="space-y-4">
            <div>
              <label class="block text-sm font-medium mb-1">Product</label>
              <select v-model="product_instance.product_id" class="w-full border px-3 py-2 rounded">
                <option v-for="product in products" :key="product.id" :value="product.id">
                  {{ product.product_name }}
                </option>
              </select>
            </div>
  
            <div>
              <label class="block text-sm font-medium mb-1">Instance Name</label>
              <input v-model="product_instance.instance_name" type="text" class="w-full border px-3 py-2 rounded" placeholder="Enter instance name" />
            </div>
  
            <div>
              <label class="block text-sm font-medium mb-1">Serial Number</label>
              <input v-model="product_instance.serial_number" type="text" class="w-full border px-3 py-2 rounded" placeholder="Enter serial number" />
            </div>
  
            <div>
              <label class="block text-sm font-medium mb-1">Brand</label>
              <select v-model="product_instance.brand_id" class="w-full border px-3 py-2 rounded">
                <option v-for="brand in brands" :key="brand.id" :value="brand.id">
                  {{ brand.brand_name }}
                </option>
              </select>
            </div>
  
            <div>
              <label class="block text-sm font-medium mb-1">Stock</label>
              <select v-model="product_instance.stock_id" class="w-full border px-3 py-2 rounded">
                <option v-for="stock in stocks" :key="stock.id" :value="stock.id">
                  {{ stock.stock_location }}
                </option>
              </select>
            </div>
  
            <div>
              <label class="block text-sm font-medium mb-1">Lot Information</label>
              <input v-model="product_instance.lot_information" type="text" class="w-full border px-3 py-2 rounded" placeholder="Enter lot information" />
            </div>
  
            <div>
              <label class="block text-sm font-medium mb-1">Warranty Terms</label>
              <textarea v-model="product_instance.warranty_terms" class="w-full border px-3 py-2 rounded" placeholder="Enter warranty terms"></textarea>
            </div>
  
            <div>
              <label class="block text-sm font-medium mb-1">Product Attribute Value</label>
              <select v-model="product_instance.product_attribute_value_id" class="w-full border px-3 py-2 rounded">
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
    products: Array,
    brands: Array,
    stocks: Array,
    attributeValues: Array,
  })
  
  const emit = defineEmits(['close', 'save'])
  
  const product_instance = ref({
    product_id: '',
    instance_name: '',
    serial_number: '',
    brand_id: '',
    stock_id: '',
    lot_information: '',
    warranty_terms: '',
    product_attribute_value_id: '',
  })
  
  const closeModal = () => {
    emit('close')
  }
  
  const submitForm = () => {
    if (product_instance.value.product_id && product_instance.value.instance_name && product_instance.value.serial_number) {
      emit('save', { ...product_instance.value })
      product_instance.value = { product_id: '', instance_name: '', serial_number: '', brand_id: '', stock_id: '', lot_information: '', warranty_terms: '', product_attribute_value_id: '' }
      closeModal()
    } else {
      alert('Please fill in all required fields.')
    }
  }
  </script>
  