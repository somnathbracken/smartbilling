<template>
  <div class="p-6">
    <h2 class="text-2xl font-bold mb-4">Sales Invoice</h2>

    <!-- Invoice Header -->
    <div class="grid grid-cols-2 gap-4 mb-4">
      <div>
        <label>Date:</label>
        <input type="date" v-model="invoice.date" class="input" />
      </div>
      <div>
        <label>Invoice No:</label>
        <input type="text" v-model="invoice.invoiceNumber" class="input" readonly />
      </div>
      <!-- Customer Search Section -->
      <div class="mb-4 w-full">
        <label class="block font-semibold mb-1">Customer</label>
        <div class="relative w-full">
          <input
            v-model="customerQuery"
            @input="searchCustomer"
            placeholder="Search Customer by Name or Phone"
            class="border p-2 w-full rounded"
          />
          <!-- Customer Suggestions -->
          <ul v-if="showSuggestions && filteredCustomers.length" class="absolute z-10 bg-white border w-full max-h-48 overflow-y-auto">
            <li
              v-for="(customer, index) in filteredCustomers"
              :key="index"
              @click="selectCustomer(customer)"
              class="p-2 hover:bg-blue-100 cursor-pointer"
            >
              {{ customer.name }} - {{ customer.phone }}
            </li>
          </ul>

          <!-- Add new customer if not found -->
          <div v-if="showSuggestions && !filteredCustomers.length" class="absolute z-10 bg-white border w-full">
            <div class="p-2 text-gray-500">
              No match found.
              <button @click="showCustomerModal = true" class="ml-2 text-blue-600 underline">Add New</button>
            </div>
          </div>
        </div>
      </div>
</div>

    <!-- Product Table -->
    <table class="table-fixed w-full border">
    <thead>
      <tr class="bg-gray-100 text-left text-sm">
        <th>Product</th>
        <th>Qty</th>
        <th>Price</th>
        <th>GST%</th>
        <th>Disc%</th>
        <th>Total</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>
      <InvoiceProductRow
        v-for="(item, index) in items"
        :key="index"
        :item="item"
        @remove="removeRow(index)"
        @focus-next="focusNextRow(index)"
        :ref="el => rowRefs[index] = el"
      />
    </tbody>
  </table>

    <!-- Summary -->
    <div class="grid grid-cols-2 gap-4 my-4">
  <div></div>
  <div class="bg-gray-100 p-4 rounded shadow text-sm">
    <p class="flex justify-between">
      <span class="text-left">Subtotal:</span>
      <span class="text-right">₹{{ subtotal.toFixed(2) }}</span>
    </p>
    <p class="flex justify-between">
      <span class="text-left">GST:</span>
      <span class="text-right">₹{{ gstTotal.toFixed(2) }}</span>
    </p>
    <p class="flex justify-between">
      <span class="text-left">Discount:</span>
      <span class="text-right">₹{{ discountTotal.toFixed(2) }}</span>
    </p>
    <hr class="my-2" />
    <p class="flex justify-between font-bold text-base">
      <span class="text-left">Total:</span>
      <span class="text-right">₹{{ Math.floor(grandTotal).toFixed(2) }}</span>
    </p>
  </div>
</div>

    <!-- Payment -->
    <div class="grid grid-cols-2 gap-4 mb-4">
      <div>
        <label>Payment Mode:</label>
        <select v-model="invoice.paymentMode" class="input">
          <option>Cash</option>
          <option>Card</option>
          <option>UPI</option>
        </select>
      </div>
      <div>
        <label>Amount Received:</label>
        <input type="number" v-model="invoice.amountReceived" class="input" />
        <p class="text-green-600 mt-1">Change: ₹{{ Math.ceil(change).toFixed(2) }}</p>
      </div>
    </div>

    <!-- Buttons -->
    <div class="flex gap-4">
      <button @click="saveInvoice" class="btn bg-green-500 text-white">Save</button>
      <button class="btn bg-gray-300">Cancel</button>
    </div>

    <AddCustomerModal v-if="showCustomerModal" @close="showCustomerModal = false" @added="fetchCustomers" />
  </div>
</template>

<script setup>
import { ref, computed, watch, onMounted, nextTick } from 'vue'
import InvoiceProductRow from '../components/modals/product/InvoiceProductRow.vue'
import AddCustomerModal from '../components/modals/product/AddCustomerModal.vue'
import { fetchCustomers, saveSalesInvoice } from '../services/salesInvoiceService'

// Reactive invoice data
const invoice = ref({
  date: new Date().toISOString().slice(0, 10),
  invoiceNumber: 'INV-' + Date.now(),
  customerId: '',
  items: [],
  discount: 0,
  paymentMode: 'Cash',
  amountReceived: 0
})

// Manage items separately to allow ref tracking and syncing
const items = ref([{ qty: 1, price: 0, gst: 0, discount: 0 }])
invoice.value.items = items.value

watch(items, (newVal) => {
  invoice.value.items = newVal
}, { deep: true })

// Modal and customer search states
const customers = ref([])
const showCustomerModal = ref(false)
const customerQuery = ref('')
const filteredCustomers = ref([])
const showSuggestions = ref(false)
const selectedCustomer = ref(null)
const newCustomer = ref({ name: '', phone: '' })

// Search and select customer
async function searchCustomer() {
  if (customerQuery.value.length < 2) {
    filteredCustomers.value = []
    showSuggestions.value = false
    return
  }
  try {
    const res = await fetch(`http://localhost:8080/api/customers/search?keyword=${encodeURIComponent(customerQuery.value)}`)
    if (!res.ok) throw new Error('Network error')
    const data = await res.json()
    filteredCustomers.value = data
    showSuggestions.value = true
  } catch (error) {
    console.error('Customer search failed:', error)
    filteredCustomers.value = []
    showSuggestions.value = false
  }
}

function selectCustomer(customer) {
  selectedCustomer.value = customer
  customerQuery.value = `${customer.name} (${customer.phone})`
  invoice.value.customer = customer
  invoice.value.customerId = customer.id
  showSuggestions.value = false
}

async function saveCustomer() {
  try {
    const res = await fetch('http://localhost:8080/api/customers', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(newCustomer.value),
    })
    if (!res.ok) throw new Error('Failed to save customer')
    const saved = await res.json()

    selectedCustomer.value = saved
    customerQuery.value = `${saved.name} (${saved.phone})`
    invoice.value.customer = saved
    invoice.value.customerId = saved.id
    newCustomer.value = { name: '', phone: '' }
    showCustomerModal.value = false
    showSuggestions.value = false
  } catch (error) {
    console.error('Save customer failed:', error)
  }
}

// Row actions
const rowRefs = []

const removeRow = (index) => {
  items.value.splice(index, 1)
}

const focusNextRow = async (index) => {
  if (index + 1 < items.value.length) {
    await nextTick()
    rowRefs[index + 1]?.$el.querySelector('input')?.focus()
  } else {
    items.value.push({ qty: 1, price: 0, gst: 0, discount: 0 })
    await nextTick()
    rowRefs[index + 1]?.$el.querySelector('input')?.focus()
  }
}

// Invoice Totals
const subtotal = computed(() =>
  invoice.value.items.reduce((sum, item) => sum + item.qty * item.price, 0)
)

const gstTotal = computed(() =>
  invoice.value.items.reduce((sum, item) => sum + (item.qty * item.price * item.gst) / 100, 0)
)

const discountTotal = computed(() =>
  invoice.value.items.reduce((sum, item) => sum + (item.qty * item.price * item.discount) / 100, 0)
)

const grandTotal = computed(() =>
  subtotal.value + gstTotal.value - discountTotal.value - Number(invoice.value.discount || 0)
)

const change = computed(() =>
  invoice.value.amountReceived - grandTotal.value
)

const roundedTotal = computed(() => Math.ceil(grandTotal.value));

// -------------------------------------------- Reset ----------------------------------
function resetForm() {
  invoice.value = {
    date: new Date().toISOString().slice(0, 10),
    invoiceNumber: 'INV-' + Date.now(),
    customerId: '',
    items: [],
    discount: 0,
    paymentMode: 'Cash',
    amountReceived: 0
  }
  customerQuery.value = ''
  selectedCustomer.value = null
  items.value = [{ qty: 1, price: 0, gst: 0, discount: 0 }]
}
// -------------------------------------------- Save invoice ----------------------------
function saveInvoice() {
  if (!invoice.value.customerId) {
    alert('Select a customer before saving.')
    return
  }
  if (!invoice.value.items.length) {
    alert('Add at least one product row.')
    return
  }

  // Validate all items
  for (const item of invoice.value.items) {
    if (!item.productId || item.qty <= 0) {
      alert('Check all product entries. Missing product or invalid quantity.')
      return
    }
  }

  console.log('Saving Invoice:', JSON.stringify(invoice.value, null, 2)) // <-- add this
  saveSalesInvoice(invoice.value)
    .then(() => {
      alert('Invoice Saved!')
    })
    .catch(err => {
      console.error('Save failed:', err)
    })
}
</script>


<style scoped>
.input {
  @apply w-full border border-gray-300 p-2 rounded;
}
.input-sm {
  @apply border border-gray-300 p-1 rounded;
}
.btn {
  @apply px-4 py-2 rounded shadow;
}
</style>
