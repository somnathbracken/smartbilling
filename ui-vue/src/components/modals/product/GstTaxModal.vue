<template>
    <div v-if="show" class="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
      <div class="bg-white p-6 rounded shadow-md w-full max-w-md">
        <h2 class="text-xl font-semibold mb-4">Add New GST Tax</h2>
        <form @submit.prevent="addGstTax">
          <div class="mb-4">
            <label class="block text-sm font-medium mb-1">Name</label>
            <input v-model="gstTax.name" type="text" class="input w-full" required />
          </div>
          <div class="mb-4">
            <label class="block text-sm font-medium mb-1">Percentage</label>
            <input v-model.number="gstTax.percentage" type="number" step="0.01" class="input w-full" required />
          </div>
          <div class="flex justify-end space-x-2">
            <button type="button" class="btn px-3 py-1 bg-gray-300" @click="$emit('close')">Cancel</button>
            <button type="submit" class="btn px-3 py-1 bg-blue-600 text-white">Save</button>
          </div>
        </form>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, defineEmits, defineProps } from 'vue'
  
  const props = defineProps({ show: Boolean })
  const emit = defineEmits(['close', 'added'])
  
  const gstTax = ref({ name: '', percentage: null })
  
  function addGstTax() {
    emit('added', { ...gstTax.value })
    gstTax.value = { name: '', percentage: null }
    emit('close')
  }
  </script>
  
  <style scoped>
  .input {
    border: 1px solid #ccc;
    border-radius: 4px;
    padding: 0.5rem;
  }
  </style>
  