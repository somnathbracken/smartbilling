<template>
    <div class="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50">
      <div class="bg-white p-6 rounded-lg shadow-lg w-96">
        <h3 class="text-xl font-semibold mb-4">Add New Customer</h3>
        <form @submit.prevent="addCustomer">
          <div class="mb-3">
            <label>Name:</label>
            <input type="text" v-model="customer.name" class="input" required />
          </div>
          <div class="mb-3">
            <label>Phone:</label>
            <input type="text" v-model="customer.phone" class="input" />
          </div>
          <div class="mb-3">
            <label>Email:</label>
            <input type="email" v-model="customer.email" class="input" />
          </div>
          <div class="mb-4">
            <label>Address:</label>
            <textarea v-model="customer.address" class="input" rows="2"></textarea>
          </div>
  
          <div class="flex justify-end gap-2">
            <button type="button" @click="$emit('close')" class="btn bg-gray-300">Cancel</button>
            <button type="submit" class="btn bg-blue-500 text-white">Save</button>
          </div>
        </form>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  import { addCustomerToDb } from '../../../services/salesInvoiceService'
  
  const emit = defineEmits(['close', 'added'])
  const customer = ref({ name: '', phone: '', email: '', address: '' })
  
  const addCustomer = async () => {
    await addCustomerToDb(customer.value)
    emit('added')
    emit('close')
  }
  </script>
  
  <style scoped>
  .input {
    @apply w-full border border-gray-300 p-2 rounded;
  }
  .btn {
    @apply px-4 py-2 rounded shadow;
  }
  </style>
  