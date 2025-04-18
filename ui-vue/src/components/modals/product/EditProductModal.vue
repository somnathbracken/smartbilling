<template>
  <div class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
    <div class="bg-white rounded-lg p-6 w-full max-w-2xl shadow-xl">
      <h2 class="text-xl font-semibold mb-4">Edit Product</h2>
      <form @submit.prevent="update">
        <!-- your form fields using localProduct -->
        <input v-model="localProduct.name" class="input" />
        <!-- other fields -->
        <div class="mt-6 flex justify-end space-x-3">
          <button type="button" class="btn-red" @click="$emit('close')">Cancel</button>
          <button type="submit" class="btn">Update</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, toRaw } from 'vue'
import axios from 'axios'
import { updateProductById } from '../../../services/ProductService';
// Props and Emits
const props = defineProps({ product: Object })
const emit = defineEmits(['close', 'update'])

const localProduct = ref({ ...toRaw(props.product) })

const update = async () => {
  try {
    alert('calling update')
    await updateProductById(`${localProduct.value.id}`,localProduct.value) //axios.put(`/api/products/${localProduct.value.id}`, localProduct.value)
    emit('update', localProduct.value)
  } catch (err) {
    console.error(err)
  }
}

</script>
<style>
.input {
  @apply border border-gray-300 p-2 rounded w-full focus:ring-2 focus:ring-blue-400 focus:outline-none;
}
</style>