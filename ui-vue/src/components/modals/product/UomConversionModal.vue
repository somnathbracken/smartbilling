<template>
    <div v-if="isOpen" class="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-40">
      <div class="bg-white p-6 rounded-xl shadow-xl w-full max-w-lg">
        <div class="flex justify-between items-center mb-4">
          <h2 class="text-xl font-semibold">Add UOM Conversion</h2>
          <button @click="closeModal" class="text-gray-500 hover:text-red-600 text-lg">&times;</button>
        </div>
  
        <form @submit.prevent="submitForm">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <!-- From UOM -->
            <div>
              <label class="block text-sm font-medium mb-1">From UOM</label>
              <select v-model="conversion.from_uom_id" class="w-full border px-3 py-2 rounded" required>
                <option disabled value="">-- Select UOM --</option>
                <option v-for="uom in uoms" :key="uom.id" :value="uom.id">{{ uom.uom_name }}</option>
              </select>
            </div>
  
            <!-- To UOM -->
            <div>
              <label class="block text-sm font-medium mb-1">To UOM</label>
              <select v-model="conversion.to_uom_id" class="w-full border px-3 py-2 rounded" required>
                <option disabled value="">-- Select UOM --</option>
                <option v-for="uom in uoms" :key="uom.id" :value="uom.id">{{ uom.uom_name }}</option>
              </select>
            </div>
  
            <!-- Conversion Rule -->
            <div class="md:col-span-2">
              <label class="block text-sm font-medium mb-1">Conversion Rule</label>
              <input
                v-model="conversion.conversion_rule"
                type="text"
                class="w-full border px-3 py-2 rounded"
                placeholder="e.g., 1 kg = 1000 g"
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
  import { ref, onMounted } from 'vue'
  
  const props = defineProps({
    isOpen: Boolean
  })
  
  const emit = defineEmits(['close', 'save'])
  
  const conversion = ref({
    from_uom_id: '',
    to_uom_id: '',
    conversion_rule: ''
  })
  
  const uoms = ref([])
  
  const closeModal = () => {
    emit('close')
  }
  
  const submitForm = () => {
    if (
      conversion.value.from_uom_id &&
      conversion.value.to_uom_id &&
      conversion.value.conversion_rule.trim()
    ) {
      emit('save', { ...conversion.value })
      resetForm()
      closeModal()
    } else {
      alert('Please complete all fields.')
    }
  }
  
  const resetForm = () => {
    conversion.value = {
      from_uom_id: '',
      to_uom_id: '',
      conversion_rule: ''
    }
  }
  
  // Dummy data - replace with API fetch if needed
  onMounted(() => {
    uoms.value = [
      { id: 1, uom_name: 'Kilogram' },
      { id: 2, uom_name: 'Gram' },
      { id: 3, uom_name: 'Liter' },
      { id: 4, uom_name: 'Milliliter' }
    ]
  })
  </script>
  