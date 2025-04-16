<template>
    <div v-if="isOpen" class="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-40">
      <div class="bg-white p-6 rounded-xl shadow-xl w-full max-w-md">
        <div class="flex justify-between items-center mb-4">
          <h2 class="text-xl font-semibold">Add Unit of Measure</h2>
          <button @click="closeModal" class="text-gray-500 hover:text-red-600 text-lg">&times;</button>
        </div>
  
        <form @submit.prevent="submitForm">
          <div class="space-y-4">
            <!-- UOM Name -->
            <div>
              <label class="block text-sm font-medium mb-1">UOM Name</label>
              <input
                v-model="uom.uom_name"
                type="text"
                class="w-full border px-3 py-2 rounded"
                placeholder="Enter UOM name (e.g., kg, pcs)"
                required
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
  import { ref } from 'vue'
  
  const props = defineProps({
    isOpen: Boolean
  })
  
  const emit = defineEmits(['close', 'save'])
  
  const uom = ref({
    uom_name: ''
  })
  
  const closeModal = () => {
    emit('close')
  }
  
  const submitForm = () => {
    if (uom.value.uom_name.trim() !== '') {
      emit('save', { ...uom.value })
      uom.value.uom_name = ''
      closeModal()
    } else {
      alert('Please enter a UOM name.')
    }
  }
  </script>
  