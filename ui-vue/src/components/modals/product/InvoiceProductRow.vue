<template>
  <tr class="relative">
    <td class="w-64 relative">
      <input
        type="text"
        v-model="searchTerm"
        @keydown.enter.prevent="handleBarcodeSearch"
        @focus="showSuggestions = true"
        @blur="hideSuggestionsWithDelay"
        placeholder="Search or Scan product"
        class="input-sm w-full"
      />
      <button @click="toggleScanner" class="text-blue-600 underline text-sm ml-2">📷 Scan Barcode</button>

      <!-- Only show when scanner is active -->
      <div v-if="showScanner" class="absolute bg-white border p-2 mt-1 z-20 shadow w-full">
        <video id="video-preview" width="300" height="200" class="rounded border" autoplay></video>
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
  <!-- Add Row Button -->
<div class="flex justify-end mt-2 mb-4">
  <button @click="addRow" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition">
    ➕ Add Row
  </button>
</div>
</template>

<script setup>
import { ref, watch, computed } from 'vue'
import { searchProducts, searchProductByBarcode } from '../../../services/salesInvoiceService'
import { BrowserMultiFormatReader } from '@zxing/browser'

const codeReader = new BrowserMultiFormatReader()
// const videoInputDevices = ref([])
const selectedDeviceId = ref(null)
const scanning = ref(false)

const props = defineProps({ item: Object })
const emit = defineEmits(['remove', 'focus-next'])

const searchTerm = ref('')
const suggestions = ref([])
const showSuggestions = ref(false)
let timeout = null

const showScanner = ref(false)
// let codeReader = null

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

let activeControls = null

const toggleScanner = () => {
  if (showScanner.value) {
    stopBarcodeScanner()
  } else {
    showScanner.value = true
    startBarcodeScanner()
  }
}

const startBarcodeScanner = async () => {
  try {
    const devices = await BrowserMultiFormatReader.listVideoInputDevices()
    if (!devices.length) {
      alert("No camera devices found")
      return
    }

    selectedDeviceId.value = devices[0].deviceId
    scanning.value = true

    activeControls = await codeReader.decodeFromVideoDevice(
      selectedDeviceId.value,
      'video-preview',
      async (result, err, controls) => {
        if (result) {
          searchTerm.value = result.getText()
          await handleBarcodeSearch()
          stopBarcodeScanner()
        } else if (err && err.name !== 'NotFoundException') {
          console.warn('QR Scan Error:', err)
        }
      }
    )
  } catch (e) {
    console.error('Camera start error:', e)
  }
}

const stopBarcodeScanner = () => {
  if (scanning.value) {
    try {
      activeControls?.stop()
    } catch (e) {
      console.warn('Stopping controls failed:', e)
    }

    const videoEl = document.getElementById('video-preview')
    if (videoEl?.srcObject) {
      videoEl.srcObject.getTracks().forEach(track => track.stop())
      videoEl.srcObject = null
    }

    scanning.value = false
    showScanner.value = false
    activeControls = null
  }
}

// ---------------------------------------- Add new line manually ---------------------------
const addRow = () => {
  items.value.push({ qty: 1, price: 0, gst: 0, discount: 0 })
}
</script>

<style scoped>
.input-sm {
  @apply border border-gray-300 p-1 rounded text-sm;
}
table {
  @apply text-sm border-collapse;
}
th, td {
  @apply border px-3 py-2;
}
thead {
  @apply bg-blue-100;
}
tbody tr:nth-child(even) {
  @apply bg-gray-50;
}
</style>
