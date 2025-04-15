<template>
  <div class="container">
    <h2>Customer Management</h2>

    <div class="form">
      <input v-model="customer.name" placeholder="Name" />
      <input v-model="customer.email" placeholder="Email" />
      <input v-model="customer.phone" placeholder="Phone" />
      <input v-model="customer.address" placeholder="Address" />
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
        <tr v-for="(c, index) in customers" :key="c.id">
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
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import CustomerService from '../services/CustomerService'

const customers = ref([])
const customer = ref({ name: '', email: '', phone: '', address: '' })
const search = ref('')

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

const editCustomer = (c) => {
  customer.value = { ...c }
}

const updateCustomer = async (customer) => {
  if (!customer.id) {
    alert("Customer ID is missing. Cannot update.");
    return;
  }

  try {
    await CustomerService.update(customer.id, customer);
    alert('Customer updated successfully.');
    fetchCustomers();
  } catch (error) {
    console.error('Error updating customer:', error);
    //alert('Update failed.');
  }
};

const deleteCustomer = async (id) => {
  try {
    await CustomerService.remove(id)
    loadCustomers()
  } catch (err) {
    console.error('Error deleting customer:', err)
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
  } catch (err) {
    console.error('Search error:', err)
  }
}

onMounted(() => {
  loadCustomers()
})
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
