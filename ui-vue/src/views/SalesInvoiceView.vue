<template>
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

<!-- Add Customer Modal -->
<div v-if="showCustomerModal" class="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50">
  <div class="bg-white p-6 rounded shadow-lg w-96">
    <h3 class="text-lg font-bold mb-4">Add New Customer</h3>
    <input v-model="newCustomer.name" placeholder="Customer Name" class="border p-2 w-full mb-2" />
    <input v-model="newCustomer.phone" placeholder="Phone Number" class="border p-2 w-full mb-2" />
    <div class="flex justify-end mt-4">
      <button @click="saveCustomer" class="bg-blue-600 text-white px-4 py-2 rounded mr-2">Save</button>
      <button @click="showCustomerModal = false" class="bg-gray-400 text-white px-4 py-2 rounded">Cancel</button>
    </div>
  </div>
</div>
<!-- Invoice Date -->
<div class="mb-4 w-full sm:w-1/2">
  <label class="block font-semibold mb-1">Invoice Date</label>
  <Datepicker
    v-model="invoice.invoiceDate"
    :format="'yyyy-MM-dd'"
    :enable-time-picker="false"
    :clearable="false"
    class="w-full"
  />
</div>

</template>
<script setup>
import { ref } from 'vue';
const invoice = ref({
  invoiceDate: new Date().toISOString().substring(0, 10), // default to today
  customer: null,
  invoiceDate: new Date(),
  items: []
});
// Reactive state
const customerQuery = ref('');
const filteredCustomers = ref([]);
const showSuggestions = ref(false);
const showCustomerModal = ref(false);

const newCustomer = ref({ name: '', phone: '' });
const selectedCustomer = ref(null);

// ------------------------Search Customer----------------------------------------------
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

//----------------------------------------------- Invoice Date ------------------
import Datepicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'
const formattedDate = new Date(invoice.value.invoiceDate).toISOString().split('T')[0];


</script>