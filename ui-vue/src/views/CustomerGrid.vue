<template>

  <div class="p-4 sm:p-8 max-w-7xl mx-auto bg-white shadow-md rounded-xl">
    <h2 class="text-2xl sm:text-3xl font-semibold text-center text-gray-800 mb-6">Customer Management</h2>

    <!-- Toggle Form Section -->
    <div class="flex justify-between items-center mb-4 sm:mb-6">
      <h3 class="text-lg font-medium">Customer Form</h3>
      <button @click="showForm = !showForm" class="text-blue-600 hover:underline sm:hidden">
        {{ showForm ? 'Hide' : 'Show' }}
      </button>
    </div>

    <!-- Form Section -->
    <div v-show="showForm" class="flex flex-wrap gap-4 justify-start sm:justify-center mb-6">
      <input v-model="customer.name" placeholder="Name" class="input-style w-full sm:w-auto" />
      <input v-model="customer.email" placeholder="Email" class="input-style w-full sm:w-auto" />
      <input v-model="customer.phone" placeholder="Phone" class="input-style w-full sm:w-auto" />
      <input v-model="customer.address" placeholder="Address" class="input-style w-full sm:w-auto" />
      <div class="flex gap-2">
        <button @click="saveCustomer" class="btn-primary">
          {{ customer.id ? 'Update' : 'Save' }}
        </button>
        <button @click="resetForm" class="btn-secondary">Reset</button>
      </div>
      <input v-model="search" @input="searchCustomers" placeholder="Search by any field..." class="input-style w-full" />
    </div>

    <!-- Toggle Table Section -->
    <div class="flex justify-between items-center mb-4">
      <h3 class="text-lg font-medium">Customer List</h3>
      <button @click="showTable = !showTable" class="text-blue-600 hover:underline sm:hidden">
        {{ showTable ? 'Hide' : 'Show' }}
      </button>
    </div>

    <!-- Table Section -->
    <div v-show="showTable" class="overflow-auto rounded-lg shadow">
      <table class="min-w-full text-sm text-left text-gray-700">
        <thead class="bg-gray-100 text-gray-600 uppercase text-xs">
          <tr>
            <th class="px-6 py-3">Name</th>
            <th class="px-6 py-3">Email</th>
            <th class="px-6 py-3">Phone</th>
            <th class="px-6 py-3">Address</th>
            <th class="px-6 py-3">Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(c, index) in paginatedCustomers" :key="c.id" class="border-b">
            <td class="px-6 py-2"><input v-model="c.name" class="input-style w-full" /></td>
            <td class="px-6 py-2"><input v-model="c.email" class="input-style w-full" /></td>
            <td class="px-6 py-2"><input v-model="c.phone" class="input-style w-full" /></td>
            <td class="px-6 py-2"><input v-model="c.address" class="input-style w-full" /></td>
            <td class="px-6 py-2 space-x-2">
              <button @click="updateCustomer(c)" class="btn-primary-sm">Update</button>
              <button @click="deleteCustomer(c.id)" class="btn-danger-sm">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Pagination -->
    <div class="flex justify-center items-center space-x-4 mt-6">
      <button @click="prevPage" :disabled="currentPage === 1" class="btn-secondary">Prev</button>
      <span class="text-gray-700 font-medium">Page {{ currentPage }} of {{ totalPages }}</span>
      <button @click="nextPage" :disabled="currentPage === totalPages" class="btn-secondary">Next</button>
    </div>

    <!-- Export Buttons -->
    <div class="flex flex-wrap justify-center space-x-4 mt-6">
      <button @click="exportToExcel" class="btn-success">Export to Excel</button>
      <button @click="exportToPDF" class="btn-warning">Export to PDF</button>
    </div>

    <p class="mt-4 text-sm text-center text-gray-500">
      Showing {{ paginatedCustomers.length }} of {{ customers.length }} customers (5 per page)
    </p>
  </div>
</template>


<script setup>
import { ref, computed, onMounted } from 'vue'
import * as XLSX from 'xlsx'
import jsPDF from 'jspdf'
import autoTable from 'jspdf-autotable'
import CustomerService from '../services/CustomerService'

const showForm = ref(true)
const showTable = ref(true)
const customers = ref([])
const customer = ref({ name: '', email: '', phone: '', address: '' })
const search = ref('')
const currentPage = ref(1)
const itemsPerPage = 5

const paginatedCustomers = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage
  return customers.value.slice(start, start + itemsPerPage)
})

const totalPages = computed(() => Math.ceil(customers.value.length / itemsPerPage))

const nextPage = () => {
  if (currentPage.value < totalPages.value) currentPage.value++
}

const prevPage = () => {
  if (currentPage.value > 1) currentPage.value--
}

const loadCustomers = async () => {
  try {
    const res = await CustomerService.getAll()
    customers.value = res.data
  } catch (err) {
    console.error('Error loading customers:', err)
  }
}

const saveCustomer = async () => {
  try {
    if (customer.value.id) {
      await CustomerService.update(customer.value.id, customer.value)
    } else {
      await CustomerService.create(customer.value)
    }
    resetForm()
    loadCustomers()
  } catch (err) {
    console.error('Error saving customer:', err)
  }
}

const updateCustomer = async (customer) => {
  if (!customer.id) return alert("Customer ID missing.")
  try {
    await CustomerService.update(customer.id, customer)
    alert("Updated successfully.")
  } catch (err) {
    console.error('Error updating:', err)
  }
}

const deleteCustomer = async (id) => {
  try {
    await CustomerService.remove(id)
    loadCustomers()
  } catch (err) {
    console.error('Error deleting:', err)
  }
}

const resetForm = () => {
  customer.value = { name: '', email: '', phone: '', address: '' }
}

const searchCustomers = async () => {
  try {
    if (!search.value.trim()) return loadCustomers()
    const res = await CustomerService.search(search.value)
    customers.value = res.data
    currentPage.value = 1
  } catch (err) {
    console.error('Search error:', err)
  }
}

const exportToExcel = () => {
  const data = customers.value.map(c => [c.name, c.email, c.phone, c.address])
  const worksheet = XLSX.utils.aoa_to_sheet([['Name', 'Email', 'Phone', 'Address'], ...data])
  const workbook = XLSX.utils.book_new()
  XLSX.utils.book_append_sheet(workbook, worksheet, 'Customers')
  XLSX.writeFile(workbook, 'customers.xlsx')
}

const exportToPDF = () => {
  const doc = new jsPDF()
  autoTable(doc, {
    head: [['Name', 'Email', 'Phone', 'Address']],
    body: customers.value.map(c => [c.name, c.email, c.phone, c.address]),
  })
  doc.save('customers.pdf')
}

onMounted(() => loadCustomers())
</script>


<style scoped>
.container {
  padding: 20px;
  max-width: 900px;
  margin: auto;
  font-family: Arial, sans-serif;
}
.form {
  margin-bottom: 20px;
}
input {
  margin: 5px;
  padding: 6px;
  width: 180px;
}
button {
  margin: 5px;
  padding: 6px 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
}
button:hover {
  background-color: #0056b3;
}
table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  border: 1px solid #ccc;
  padding: 8px;
}

/* Add these to your tailwind base layer or a global style file */
.input-style {
  @apply border border-gray-300 rounded-md px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400 text-sm;
}
.btn-primary {
  @apply bg-blue-600 text-white px-4 py-2 rounded-md hover:bg-blue-700 text-sm font-medium;
}
.btn-secondary {
  @apply bg-gray-200 text-gray-700 px-4 py-2 rounded-md hover:bg-gray-300 text-sm font-medium;
}
.btn-primary-sm {
  @apply bg-blue-500 text-white px-3 py-1 rounded hover:bg-blue-600 text-xs;
}
.btn-danger-sm {
  @apply bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600 text-xs;
}
.btn-success {
  @apply bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600 text-sm font-medium;
}
.btn-warning {
  @apply bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600 text-sm font-medium;
}
</style>
