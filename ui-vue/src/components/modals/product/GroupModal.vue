<template>
  <div
    v-if="show"
    class="fixed inset-0 z-50 flex items-center justify-center bg-gray-900 bg-opacity-50"
  >
    <div class="bg-white rounded-lg shadow-lg w-full max-w-md p-6 relative">
      <h2 class="text-xl font-semibold mb-4">Add New Group</h2>
      <form @submit.prevent="saveGroup">
        <div class="mb-4">
          <label class="block text-sm font-medium text-gray-700">Group Name</label>
          <input
            v-model="groupName"
            type="text"
            class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm p-2 focus:ring-blue-500 focus:border-blue-500"
            placeholder="Enter group name"
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
        <button
          @click="closeModal"
          class="absolute top-2 right-2 text-gray-500 hover:text-red-600"
        >
          ✕
        </button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, defineProps, defineEmits } from 'vue'

const props = defineProps({ show: Boolean })
const emit = defineEmits(['close', 'saved'])
const groupName = ref('')

function saveGroup() {
  if (groupName.value.trim()) {
    emit('saved', { name: groupName.value })
    closeModal()
  }
}

function closeModal() {
  emit('close')
  groupName.value = ''
}
</script>
