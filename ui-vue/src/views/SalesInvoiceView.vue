<template>
    <div class="p-6">
      <h2 class="text-2xl font-bold mb-4">Create Sales Invoice</h2>
  
      <!-- Customer Selection -->
      <div class="mb-4">
        <label class="block font-medium mb-1">Customer</label>
        <select v-model="invoice.customer.id" class="border rounded p-2 w-full">
          <option value="" disabled>Select Customer</option>
          <option v-for="cust in customers" :key="cust.id" :value="cust.id">{{ cust.name }}</option>
        </select>
      </div>
  
      <!-- Invoice Date -->
      <div class="mb-4">
        <label class="block font-medium mb-1">Invoice Date</label>
        <input type="date" v-model="invoice.invoiceDate" class="border rounded p-2 w-full" />
      </div>
  
      <!-- Items Table -->
      <div class="mb-4">
        <h3 class="font-semibold mb-2">Invoice Items</h3>
        <table class="w-full border">
          <thead class="bg-gray-100">
            <tr>
              <th class="p-2 border">Product</th>
              <th class="p-2 border">Quantity</th>
              <th class="p-2 border">Unit Price</th>
              <th class="p-2 border">GST %</th>
              <th class="p-2 border">Discount</th>
              <th class="p-2 border">Total</th>
              <th class="p-2 border"></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item, index) in invoice.items" :key="index">
              <td>
                <select v-model="item.product.id" class="p-2 border">
                  <option value="">Select</option>
                  <option v-for="p in products" :key="p.id" :value="p.id">{{ p.name }}</option>
                </select>
              </td>
              <td><input type="number" v-model.number="item.quantity" class="p-2 border w-20" /></td>
              <td><input type="number" v-model.number="item.unitPrice" class="p-2 border w-24" /></td>
              <td><input type="number" v-model.number="item.gstPercentage" class="p-2 border w-16" /></td>
              <td><input type="number" v-model.number="item.discount" class="p-2 border w-20" /></td>
              <td class="text-right pr-4">{{ calculateItemTotal(item).toFixed(2) }}</td>
              <td>
                <button @click="removeItem(index)" class="text-red-600 hover:underline">Remove</button>
              </td>
            </tr>
          </tbody>
        </table>
        <button @click="addItem" class="mt-2 bg-blue-500 text-white px-3 py-1 rounded">+ Add Item</button>
      </div>
  
      <!-- Summary -->
      <div class="mb-4 flex justify-between">
        <div>Total GST: ₹{{ totalGST.toFixed(2) }}</div>
        <div>Discount: ₹{{ invoice.discount.toFixed(2) }}</div>
        <div><strong>Grand Total: ₹{{ grandTotal.toFixed(2) }}</strong></div>
      </div>
  
      <!-- Submit Button -->
      <button @click="submitInvoice" class="bg-green-600 text-white px-4 py-2 rounded">Save Invoice</button>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted, computed } from 'vue'
  import axios from 'axios'
  
  // Reactive invoice data
  const invoice = ref({
    customer: { id: '' },
    invoiceDate: new Date().toISOString().substring(0, 10),
    discount: 0,
    items: []
  })
  
  const customers = ref([])
  const products = ref([])
  
  const addItem = () => {
    invoice.value.items.push({
      product: { id: '' },
      quantity: 1,
      unitPrice: 0,
      gstPercentage: 0,
      discount: 0
    })
  }
  
  const removeItem = (index) => {
    invoice.value.items.splice(index, 1)
  }
  
  const calculateItemTotal = (item) => {
    const subtotal = item.quantity * item.unitPrice
    const gst = (subtotal * item.gstPercentage) / 100
    const discount = item.discount || 0
    return subtotal + gst - discount
  }
  
  const totalGST = computed(() =>
    invoice.value.items.reduce((sum, item) => {
      const gst = (item.quantity * item.unitPrice * item.gstPercentage) / 100
      return sum + gst
    }, 0)
  )
  
  const grandTotal = computed(() => {
    const total = invoice.value.items.reduce((sum, item) => {
      return sum + calculateItemTotal(item)
    }, 0)
    return total - (invoice.value.discount || 0)
  })
  
  // Fetch customers and products
  onMounted(async () => {
    customers.value = (await axios.get('http://localhost:8080/api/customers/listAll')).data
    products.value = (await axios.get('http://localhost:8082/api/products')).data
  })
  
  // Submit to backend
  const submitInvoice = async () => {
    const payload = {
      ...invoice.value,
      totalGST: totalGST.value,
      grandTotal: grandTotal.value
    }
  
    try {
      await axios.post('http://localhost:8083/api/salesinvoices', payload)
      alert('Invoice saved successfully!')
      invoice.value = {
        customer: { id: '' },
        invoiceDate: new Date().toISOString().substring(0, 10),
        discount: 0,
        items: []
      }
    } catch (err) {
      console.error(err)
      alert('Error saving invoice!')
    }
  }
  
  </script>
  
  <style scoped>
  table, th, td {
    border-collapse: collapse;
  }
  </style>
  