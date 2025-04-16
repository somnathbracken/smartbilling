<template>
  <div
    v-if="isOpen"
    class="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-40"
  >
    <div class="bg-white p-6 rounded-xl shadow-xl w-full max-w-md">
      <div class="flex justify-between items-center mb-4">
        <h2 class="text-xl font-semibold">Add Generic Product</h2>
        <button @click="closeModal" class="text-gray-500 hover:text-red-600 text-lg">
          &times;
        </button>
      </div>

      <form @submit.prevent="submitForm">
        <div class="space-y-4">
          <div>
            <label class="block text-sm font-medium mb-1">Generic Name</label>
            <input
              v-model="generic.generic_name"
              type="text"
              class="w-full border px-3 py-2 rounded focus:outline-none focus:ring focus:ring-blue-300"
              placeholder="Enter generic product name"
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
import { ref, watch } from 'vue'

const props = defineProps({
  isOpen: Boolean,
})

const emit = defineEmits(['close', 'save'])

const generic = ref({
  generic_name: '',
})

const closeModal = () => {
  emit('close')
  generic.value.generic_name = ''
}

const submitForm = () => {
  if (generic.value.generic_name.trim()) {
    emit('save', { ...generic.value })
    closeModal()
  } else {
    alert('Please enter the generic name.')
  }
}
</script>
