<template>
  <div
    v-if="show"
    class="fixed inset-0 z-50 flex items-center justify-center bg-gray-900 bg-opacity-50"
  >
    <div class="bg-white rounded-lg shadow-lg w-full max-w-md p-6 relative">
      <h2 class="text-xl font-semibold mb-4">Add New Category</h2>
      <form @submit.prevent="saveCategory">
        <div class="mb-4">
          <label class="block text-sm font-medium text-gray-700">Category Name</label>
          <input
            v-model="categoryName"
            type="text"
            class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm p-2 focus:ring-blue-500 focus:border-blue-500"
            placeholder="Enter category name"
          />
        </div>
        <div class="flex justify-end space-x-2">
          <button
            type="button"
            @click="closeModal"
            class="px-4 py-2 bg-gray-300 text-gray-700 rounded hover:bg-gray-400"
          >
            Cancel
          </button>
          <button
            type="submit"
            class="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
          >
            Save
          </button>
        </div>
      </form>
      <!-- Close button (top-right) -->
      <button
        @click="closeModal"
        class="absolute top-2 right-2 text-gray-500 hover:text-red-600"
      >
        ✕
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, defineProps, defineEmits, watch } from 'vue'

const props = defineProps({
  show: Boolean
})

const emit = defineEmits(['close', 'saved'])

const categoryName = ref('')

function saveCategory() {
  if (categoryName.value.trim()) {
    emit('saved', { name: categoryName.value })
    closeModal()
  }
}

function closeModal() {
  emit('close')
  categoryName.value = ''
}
</script>
