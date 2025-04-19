<template>
  <tr class="relative">
    <td class="w-64">
      <input
        type="text"
        v-model="searchTerm"
        @focus="showSuggestions = true"
        @blur="hideSuggestionsWithDelay"
        placeholder="Search product"
        class="input-sm w-full"
      />
      <!-- Suggestions Dropdown -->
      <ul
        v-if="showSuggestions && suggestions.length"
        class="absolute bg-white border w-full mt-1 max-h-40 overflow-y-auto z-10 shadow"
      >
        <li
          v-for="(product, index) in suggestions"
          :key="index"
          @mousedown.prevent="selectProduct(product)"
          class="px-3 py-2 hover:bg-blue-100 cursor-pointer text-sm"
        >
          {{ product.name }} ({{ product.code }})
        </li>
      </ul>
    </td>
    <td><input type="number" v-model.number="item.qty" class="input-sm w-20" min="1" /></td>
    <td><input type="number" v-model.number="item.price" class="input-sm w-24" step="0.01" /></td>
    <td><input type="number" v-model.number="item.gst" class="input-sm w-16" step="0.01" /></td>
    <td><input type="number" v-model.number="item.discount" class="input-sm w-16" step="0.01" /></td>
    <td>₹{{ itemTotal.toFixed(2) }}</td>
    <td><button @click="$emit('remove')" class="text-red-500 hover:text-red-700 text-sm">🗑</button></td>
  </tr>
</template>

<script setup>
import { ref, watch, computed } from 'vue'
import { searchProducts } from '../../../services/salesInvoiceService'

const props = defineProps({
  item: Object
})

const searchTerm = ref('')
const suggestions = ref([])
const showSuggestions = ref(false)
let timeout = null

const fetchSuggestions = async () => {
  if (searchTerm.value.length < 2) return
  suggestions.value = await searchProducts(searchTerm.value)
}

watch(searchTerm, () => {
  clearTimeout(timeout)
  timeout = setTimeout(fetchSuggestions, 300)
})

const selectProduct = (product) => {
  props.item.product = product.name
  props.item.productId = product.id
  props.item.price = product.unitPrice
  props.item.gst = product.gstPercentage
  props.item.discount = product.discountPercentage || 0
  searchTerm.value = product.name
  showSuggestions.value = false
}

const hideSuggestionsWithDelay = () => {
  setTimeout(() => showSuggestions.value = false, 200)
}

const itemTotal = computed(() => {
  const price = props.item.price || 0
  const qty = props.item.qty || 0
  const gst = props.item.gst || 0
  const discount = props.item.discount || 0
  let total = price * qty
  total = total - (total * discount / 100)
  return total + (total * gst / 100)
})

// Add product in row items

</script>

<style scoped>
.input-sm {
  @apply border border-gray-300 p-1 rounded text-sm;
}
</style>
