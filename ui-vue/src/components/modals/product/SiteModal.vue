<template>
  <div v-if="show" class="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
    <div class="bg-white p-6 rounded shadow-md w-full max-w-md">
      <h2 class="text-lg font-semibold mb-4">Add New Site</h2>
      <form @submit.prevent="addItem">
        <div class="mb-3">
          <label class="block mb-1 font-medium">Site Name</label>
          <input v-model="item.name" type="text" class="input w-full" required />
        </div>
        <div class="flex justify-end space-x-2">
          <button type="button" class="btn bg-gray-300 px-3 py-1" @click="$emit('close')">Cancel</button>
          <button type="submit" class="btn bg-blue-600 text-white px-3 py-1">Add</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, defineProps, defineEmits } from 'vue'

const props = defineProps({ show: Boolean })
const emit = defineEmits(['close', 'added'])

const item = ref({ id: null, name: '' })

function addItem() {
  item.value.id = Date.now()
  emit('added', { ...item.value })
  emit('close')
  item.value = { id: null, name: '' }
}
</script>

<style scoped>
.input {
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 0.5rem;
}
</style>
