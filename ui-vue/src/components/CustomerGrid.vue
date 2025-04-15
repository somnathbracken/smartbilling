<template>
  <div class="container">
    <h2>Customer Management</h2>

    <div class="form">
      <input v-model="customer.name" placeholder="Name" />
      <input v-model="customer.email" placeholder="Email" />
      <input v-model="customer.phone" placeholder="Phone" />
      <input v-model="customer.address" placeholder="Phone" />
      <button @click="saveCustomer">{{ customer.id ? 'Update' : 'Save' }}</button>
      <button @click="resetForm">Reset</button>
      <input v-model="search" @input="searchCustomers" placeholder="Search by any field..." />
    </div>

    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Email</th>
          <th>Phone</th>
          <th>Address</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(c, index) in paginatedCustomers" :key="c.id">
          <td><input v-model="c.name" /></td>
          <td><input v-model="c.email" /></td>
          <td><input v-model="c.phone" /></td>
          <td><input v-model="c.address" /></td>
          <td>            
            <button @click="updateCustomer(c)">Update</button>
            <button @click="deleteCustomer(c.id)">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
    <div style="margin-top: 10px;">
      <button @click="prevPage" :disabled="currentPage === 1">Prev</button>
      Page {{ currentPage }} of {{ totalPages }}
      <button @click="nextPage" :disabled="currentPage === totalPages">Next</button>
    </div>

    <div style="margin-top: 20px;">
      <button @click="exportToExcel">Export to Excel</button>
      <button @click="exportToPDF">Export to PDF</button>
    </div>
  </div>
  <p>Showing {{ paginatedCustomers.length }} of {{ customers.length }} customers (5 per page)</p>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import * as XLSX from 'xlsx'
import jsPDF from 'jspdf'
import autoTable from 'jspdf-autotable'
import CustomerService from '../services/CustomerService'

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
</style>
