<template>
  <div class="container">
    <h2>Customer Management</h2>

    <form @submit.prevent="saveCustomer">
      <input v-model="customer.name" placeholder="Name" required />
      <input v-model="customer.email" placeholder="Email" required />
      <input v-model="customer.phone" placeholder="Phone" required />
      <button type="submit">Save</button>
    </form>

    <div class="search">
      <input v-model="searchTerm" placeholder="Search by name" />
      <button @click="searchCustomers">Search</button>
    </div>

    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Email</th>
          <th>Phone</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(cust, index) in customers" :key="cust.id">
          <td><input v-model="cust.name" /></td>
          <td><input v-model="cust.email" /></td>
          <td><input v-model="cust.phone" /></td>
          <td>
            <button @click="updateCustomer(cust)">Update</button>
            <button @click="deleteCustomer(cust.id)">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import CustomerService from '../services/CustomerService'

const customer = ref({ name: '', email: '', phone: '' })
const customers = ref([])
const searchTerm = ref('')

const fetchCustomers = async () => {
  customers.value = await CustomerService.getAllCustomers()
}

const saveCustomer = async () => {
  await CustomerService.saveCustomer(customer.value)
  customer.value = { name: '', email: '', phone: '' }
  await fetchCustomers()
}

const updateCustomer = async (cust) => {
  await CustomerService.updateCustomer(cust)
  await fetchCustomers()
}

const deleteCustomer = async (id) => {
  await CustomerService.deleteCustomer(id)
  await fetchCustomers()
}

const searchCustomers = async () => {
  if (searchTerm.value.trim() === '') {
    await fetchCustomers()
  } else {
    customers.value = await CustomerService.searchCustomers(searchTerm.value)
  }
}

onMounted(fetchCustomers)
</script>

<style scoped>
.container {
  padding: 20px;
  font-family: Arial, sans-serif;
}

input {
  margin: 5px;
  padding: 6px;
  border-radius: 4px;
  border: 1px solid #ccc;
}

button {
  margin: 5px;
  padding: 6px 12px;
  border: none;
  background-color: #007acc;
  color: white;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #005fa3;
}

table {
  width: 100%;
  margin-top: 20px;
  border-collapse: collapse;
}

th,
td {
  padding: 10px;
  border: 1px solid #ddd;
}
</style>
