<template>
    <div class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white p-6 rounded-xl shadow-xl w-full max-w-md">
        <div class="flex justify-between items-center mb-4">
          <h2 class="text-xl font-semibold">Add New Site</h2>
          <button @click="$emit('close')" class="text-gray-500 hover:text-red-500">&times;</button>
        </div>
  
        <form @submit.prevent="submitForm">
          <div class="mb-4">
            <label for="site_code" class="block text-sm font-medium text-gray-700">Site Code</label>
            <input type="text" id="site_code" v-model="form.site_code" class="mt-1 block w-full border border-gray-300 rounded-md p-2" required />
          </div>
  
          <div class="mb-4">
            <label for="site_name" class="block text-sm font-medium text-gray-700">Site Name</label>
            <input type="text" id="site_name" v-model="form.site_name" class="mt-1 block w-full border border-gray-300 rounded-md p-2" required />
          </div>
  
          <div class="flex justify-end">
            <button type="button" @click="$emit('close')" class="mr-2 px-4 py-2 border rounded-md text-gray-600 hover:bg-gray-100">Cancel</button>
            <button type="submit" class="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700">Save</button>
          </div>
        </form>
      </div>
    </div>
  </template>
  
  <script setup>
  import { reactive } from 'vue'
  
  const form = reactive({
    site_code: '',
    site_name: ''
  })
  
  function submitForm() {
    console.log('New site data submitted:', form)
    // Emit saved data or call service API
    // $emit('save', { ...form })
    // Clear form or close modal
    alert('Site saved (mock)')
  }
  </script>
  
  <style scoped>
  /* Optional additional styles */
  </style>
  