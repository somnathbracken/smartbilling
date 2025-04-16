<template>
  <div v-if="show" class="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
    <div class="bg-white p-6 rounded shadow-md w-full max-w-lg">
      <h2 class="text-xl font-semibold mb-4">Add New Product Lot</h2>
      <form @submit.prevent="addLot">
        <div class="mb-3">
          <label class="block mb-1 font-medium">Lot Code / Batch Code</label>
          <input v-model="lot.lot_code" type="text" class="input w-full" required />
        </div>
        <div class="mb-3">
          <label class="block mb-1 font-medium">Date of Manufacture</label>
          <input v-model="lot.date_manufactured" type="date" class="input w-full" />
        </div>
        <div class="mb-3">
          <label class="block mb-1 font-medium">Date of Expiry</label>
          <input v-model="lot.date_expiry" type="date" class="input w-full" />
        </div>
        <div class="mb-4">
          <label class="block mb-1 font-medium">Product Attribute Value (optional)</label>
          <select v-model="lot.product_attribute_value_id" class="input w-full">
            <option value="">-- None --</option>
            <option v-for="attr in productAttributes" :key="attr.id" :value="attr.id">
              {{ attr.name }}
            </option>
          </select>
        </div>
        <div class="flex justify-end space-x-2">
          <button type="button" class="btn px-3 py-1 bg-gray-300" @click="$emit('close')">Cancel</button>
          <button type="submit" class="btn px-3 py-1 bg-blue-600 text-white">Add Lot</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, defineProps, defineEmits } from 'vue'

const props = defineProps({
  show: Boolean,
  productAttributes: {
    type: Array,
    default: () => [],
  }
})
const emit = defineEmits(['close', 'added'])

const lot = ref({
  id: null,
  lot_code: '',
  date_manufactured: '',
  date_expiry: '',
  product_attribute_value_id: ''
})

function addLot() {
  lot.value.id = Date.now()
  emit('added', { ...lot.value })
  emit('close')
  lot.value = {
    id: null,
    lot_code: '',
    date_manufactured: '',
    date_expiry: '',
    product_attribute_value_id: ''
  }
}
</script>

<style scoped>
.input {
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 0.5rem;
}
</style>
