<template>
    <div v-if="isOpen" class="fixed inset-0 flex items-center justify-center z-50 bg-black bg-opacity-40">
      <div class="bg-white rounded-xl shadow-lg p-6 w-full max-w-lg">
        <div class="flex justify-between items-center mb-4">
          <h2 class="text-xl font-semibold">Add Warehouse</h2>
          <button @click="closeModal" class="text-gray-500 hover:text-red-600 text-lg">&times;</button>
        </div>
        <form @submit.prevent="submitForm">
          <div class="grid grid-cols-1 gap-4">
            <div>
              <label class="block text-sm font-medium mb-1">Site</label>
              <select v-model="warehouse.site_id" class="w-full border rounded px-3 py-2">
                <option disabled value="">-- Select Site --</option>
                <option v-for="site in sites" :key="site.id" :value="site.id">
                  {{ site.site_name }}
                </option>
              </select>
            </div>
  
            <div>
              <label class="block text-sm font-medium mb-1">Warehouse Code</label>
              <input v-model="warehouse.warehouse_code" type="text" class="w-full border rounded px-3 py-2" />
            </div>
  
            <div>
              <label class="block text-sm font-medium mb-1">Warehouse Name</label>
              <input v-model="warehouse.warehouse_name" type="text" class="w-full border rounded px-3 py-2" />
            </div>
          </div>
  
          <div class="flex justify-end gap-2 mt-6">
            <button type="button" @click="closeModal" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-semibold py-2 px-4 rounded">
              Cancel
            </button>
            <button type="submit" class="bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2 px-4 rounded">
              Save
            </button>
          </div>
        </form>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, watch, onMounted } from 'vue'
  
  const props = defineProps({
    isOpen: Boolean,
    sites: Array,
  })
  
  const emit = defineEmits(['close', 'save'])
  
  const warehouse = ref({
    site_id: '',
    warehouse_code: '',
    warehouse_name: '',
  })
  
  const closeModal = () => {
    emit('close')
  }
  
  const submitForm = () => {
    if (warehouse.value.site_id && warehouse.value.warehouse_code && warehouse.value.warehouse_name) {
      emit('save', { ...warehouse.value })
      closeModal()
      warehouse.value = { site_id: '', warehouse_code: '', warehouse_name: '' }
    } else {
      alert('Please fill all required fields.')
    }
  }
  </script>
  