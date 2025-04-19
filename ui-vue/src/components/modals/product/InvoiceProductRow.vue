<template>
  <tr class="relative">
    <td class="w-64">
      <div class="flex flex-col space-y-1">
        <input
          type="text"
          v-model="searchTerm"
          @keydown.enter.prevent="handleBarcodeSearch"
          @focus="showSuggestions = true"
          @blur="hideSuggestionsWithDelay"
          placeholder="Search or Scan product"
          class="input-sm w-full"
        />
        <button @click="startBarcodeScanner" class="text-xs text-blue-600 hover:underline self-start">
          📷 Scan with Camera
        </button>
        <div v-if="showScanner" class="relative">
          <video id="barcode-video" class="w-full border rounded"></video>
          <button
            @click="stopBarcodeScanner"
            class="absolute top-1 right-1 bg-red-500 text-white px-2 py-1 text-xs rounded"
          >
            ✖ Close
          </button>
        </div>
      </div>

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
          {{ product.name }} ({{ product.productCode }})
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
import { searchProducts, searchProductByBarcode } from '../../../services/salesInvoiceService'
import { BrowserMultiFormatReader } from '@zxing/browser'

const props = defineProps({ item: Object })
const emit = defineEmits(['remove', 'focus-next'])

const searchTerm = ref('')
const suggestions = ref([])
const showSuggestions = ref(false)
let timeout = null

const showScanner = ref(false)
let codeReader = null

const fetchSuggestions = async () => {
  if (searchTerm.value.length < 2) return
  suggestions.value = await searchProducts(searchTerm.value)
}

watch(searchTerm, () => {
  clearTimeout(timeout)
  timeout = setTimeout(fetchSuggestions, 300)
})

const handleBarcodeSearch = async () => {
  if (!searchTerm.value) return
  try {
    const result = await searchProductByBarcode(searchTerm.value)
    const product = Array.isArray(result) ? result[0] : result
    if (product) {
      selectProduct(product)
      playBeep()
      emit('focus-next')
    } else {
      alert('Product not found')
    }
  } catch (err) {
    console.error('Barcode fetch error', err)
  }
}

const selectProduct = (product) => {
  props.item.product = product.name
  props.item.productId = product.id
  props.item.price = parseFloat(product.unitPrice) || 0
  props.item.gst = parseFloat(product.gstPercentage) || 0
  props.item.discount = parseFloat(product.discounts?.replace('%', '') || 0)
  props.item.qty = 1
  searchTerm.value = product.name
  showSuggestions.value = false
}

const playBeep = () => {
  const beep = new Audio('data:audio/wav;base64,UklGRiQAAABXQVZFZm10IBAAAAABAAEAQB8AAIA+AAACABAAZGF0YQgAAAAA')
  beep.play()
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

const startBarcodeScanner = async () => {
  showScanner.value = true
  try {
    codeReader = new BrowserMultiFormatReader()
    const devices = await BrowserMultiFormatReader.listVideoInputDevices()
    const selectedDeviceId = devices[0]?.deviceId
    if (!selectedDeviceId) {
      alert('No camera found')
      return
    }
    codeReader.decodeFromVideoDevice(
      selectedDeviceId,
      'barcode-video',
      async (result, err) => {
        if (result) {
          console.log('Barcode scanned:', result.getText())
          searchTerm.value = result.getText()
          await handleBarcodeSearch()
          stopBarcodeScanner()
        } else if (err && !err.message.includes('No MultiFormat Readers')) {
          console.error('QR Scan Error:', err)
        }
      }
    )
  } catch (e) {
    console.error('Camera init failed', e)
    alert('Camera failed to start')
    showScanner.value = false
  }
}

const stopBarcodeScanner = () => {
  try {
    codeReader?.reset()
  } catch (e) {
    console.warn('Scanner reset error:', e)
  }
  showScanner.value = false
}
</script>

<style scoped>
.input-sm {
  @apply border border-gray-300 p-1 rounded text-sm;
}
</style>
