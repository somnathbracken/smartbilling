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

<!-- Add Barcode Product Scanning support -->
  <div>
    <div id="reader" style="width: 100%; max-width: 350px;"></div>
    <button @click="stopScanner" class="mt-2 bg-red-500 text-white px-3 py-1 rounded text-sm">Stop Scanner</button>
  </div>
  <div class="mb-4">
    <input
      type="text"
      v-model="barcode"
      @keydown.enter.prevent="handleBarcodeScan"
      placeholder="Scan barcode here..."
      class="border p-2 rounded w-72 text-sm"
    />
  </div>

    <!-- Product Table -->
    <table class="table-auto w-full mb-4 border">
      <thead class="bg-gray-100">
        <tr>
          <th>Product</th>
          <th>Qty</th>
          <th>Price</th>
          <th>GST %</th>
          <th>Discount %</th>
          <th>Total</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <InvoiceProductRow
          v-for="(item, index) in invoice.items"
          :key="index"
          :item="item"
          @remove="removeItem(index)"
        />
      </tbody>
    </table>
    <button @click="addItem" class="btn btn-sm bg-blue-500 text-white">+ Add Product</button>

    <!-- Summary -->
    <div class="grid grid-cols-2 gap-4 my-4">
      <div></div>
      <div class="bg-gray-100 p-4 rounded shadow">
        <p>Subtotal: ₹{{ subtotal }}</p>
        <p>GST: ₹{{ gstTotal }}</p>
        <p>Discount: ₹<input type="number" v-model="invoice.discount" class="w-20 input-sm" /></p>
        <hr class="my-2" />
        <p class="font-bold">Total: ₹{{ grandTotal }}</p>
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
        <p class="text-green-600 mt-1">Change: ₹{{ change }}</p>
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
import { ref, computed, onMounted } from 'vue'
import InvoiceProductRow from '../components/modals/product/InvoiceProductRow.vue'
import AddCustomerModal from '../components/modals/product/AddCustomerModal.vue'
import { fetchCustomers, saveSalesInvoice } from '../services/salesInvoiceService'

const invoice = ref({
  date: new Date().toISOString().slice(0, 10),
  invoiceNumber: 'INV-' + Date.now(),
  customerId: '',
  items: [],
  discount: 0,
  paymentMode: 'Cash',
  amountReceived: 0
})

const customers = ref([])
const showCustomerModal = ref(false)

// onMounted(fetchCustomersList)
// function fetchCustomersList() {
//   fetchCustomers().then(data => customers.value = data)
// }

function addItem() {
  invoice.value.items.push({ product: '', qty: 1, price: 0, gst: 0 })
}

function removeItem(index) {
  invoice.value.items.splice(index, 1)
}

const subtotal = computed(() =>
  invoice.value.items.reduce((sum, item) => sum + item.qty * item.price, 0)
)
const gstTotal = computed(() =>
  invoice.value.items.reduce((sum, item) => sum + (item.qty * item.price * item.gst) / 100, 0)
)
const grandTotal = computed(() =>
  subtotal.value + gstTotal.value - invoice.value.discount
)
const change = computed(() =>
  invoice.value.amountReceived - grandTotal.value
)

function saveInvoice() {
  saveSalesInvoice(invoice.value).then(() => {
    alert('Invoice Saved!')
    // reset or redirect
  })
}

// ------------------------Search Customer----------------------------------------------
// Reactive state
const customerQuery = ref('');
const filteredCustomers = ref([]);
const showSuggestions = ref(false);
// const showCustomerModal = ref(false);

const newCustomer = ref({ name: '', phone: '' });
const selectedCustomer = ref(null);

async function searchCustomer() {
  if (customerQuery.value.length < 2) {
    filteredCustomers.value = [];
    showSuggestions.value = false;
    return;
  }

  try {
    const res = await fetch(`http://localhost:8080/api/customers/search?keyword=${encodeURIComponent(customerQuery.value)}`);
    if (!res.ok) throw new Error('Network error');
    const data = await res.json();
    filteredCustomers.value = data;
    showSuggestions.value = true;
  } catch (error) {
    console.error('Customer search failed:', error);
    filteredCustomers.value = [];
    showSuggestions.value = false;
  }
}

// Select existing customer
function selectCustomer(customer) {
  selectedCustomer.value = customer;
  customerQuery.value = `${customer.name} (${customer.phone})`;
  showSuggestions.value = false;

  // Optional: set this into your invoice object
  invoice.customer = customer;
}

// Save new customer
async function saveCustomer() {
  try {
    const res = await fetch('http://localhost:8080/api/customers', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(newCustomer.value),
    });
    if (!res.ok) throw new Error('Failed to save customer');
    const saved = await res.json();

    selectedCustomer.value = saved;
    customerQuery.value = `${saved.name} (${saved.phone})`;
    invoice.customer = saved; // assign to invoice

    newCustomer.value = { name: '', phone: '' };
    showCustomerModal.value = false;
    showSuggestions.value = false;
  } catch (error) {
    console.error('Save customer failed:', error);
  }
}
//-------------------------------------------- End of Search Customer -------------------------------------

//--------------------------------------------- Bar code Support ------------------------------------------
import { getProductByBarcode } from '../services/salesInvoiceService'

const barcode = ref('')
const invoiceItems = ref([]) // assuming you're managing invoice items here

const handleBarcodeScan = async () => {
  if (!barcode.value) return

  const product = await getProductByBarcode(barcode.value.trim())
  if (product) {
    invoiceItems.value.push({
      productId: product.id,
      product: product.name,
      qty: 1,
      price: product.unitPrice,
      gst: product.gstPercentage,
      discount: product.discountPercentage || 0
    })
  } else {
    alert('Product not found!')
  }

  barcode.value = ''
}

// Scanning support for mobile/webcam
import { onBeforeUnmount } from 'vue'
import { Html5Qrcode } from 'html5-qrcode'

const emit = defineEmits(['onScan'])
let html5QrCode

const startScanner = async () => {
  const config = { fps: 10, qrbox: { width: 250, height: 250 } }

  html5QrCode = new Html5Qrcode("reader")
  await html5QrCode.start(
    { facingMode: "environment" }, // back camera
    config,
    (decodedText) => {
      emit('onScan', decodedText)
      stopScanner() // stop after successful scan
    },
    (errorMessage) => {
      // console.log(`Scan error: ${errorMessage}`)
    }
  )
}

const stopScanner = () => {
  if (html5QrCode) html5QrCode.stop().then(() => html5QrCode.clear())
}

onMounted(startScanner)
onBeforeUnmount(stopScanner)

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
