<template>
    <div class="p-6 space-y-4">
      <!-- Invoice Header -->
      <div class="grid grid-cols-2 gap-4">
        <div>
          <label class="block text-sm font-medium">Invoice Number</label>
          <input v-model="invoice.invoiceNumber" class="w-full border rounded p-2" type="text" />
        </div>
        <div>
          <label class="block text-sm font-medium">Invoice Date</label>
          <input v-model="invoice.invoiceDate" class="w-full border rounded p-2" type="date" />
        </div>
        <div>
          <label class="block text-sm font-medium">Customer</label>
          <select v-model="invoice.customerId" class="w-full border rounded p-2">
            <option v-for="customer in customers" :key="customer.id" :value="customer.id">
              {{ customer.name }}
            </option>
          </select>
          <button class="text-sm text-blue-600 underline mt-1" @click="openCustomerModal">+ Add Customer</button>
        </div>
        <div>
          <label class="block text-sm font-medium">Billing Address</label>
          <textarea v-model="invoice.billingAddress" class="w-full border rounded p-2"></textarea>
        </div>
        <div>
          <label class="block text-sm font-medium">Shipping Address</label>
          <textarea v-model="invoice.shippingAddress" class="w-full border rounded p-2"></textarea>
        </div>
      </div>
  
      <!-- Product Line Items -->
      <div class="mt-6">
        <table class="min-w-full table-auto border border-gray-200">
          <thead>
            <tr class="bg-gray-100">
              <th class="p-2 border">Product</th>
              <th class="p-2 border">Qty</th>
              <th class="p-2 border">Unit Price</th>
              <th class="p-2 border">GST %</th>
              <th class="p-2 border">Discount %</th>
              <th class="p-2 border">Total</th>
              <th class="p-2 border">Actions</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item, index) in invoice.items" :key="index">
              <td class="p-2 border">
                <select v-model="item.productId" class="w-full border rounded p-1">
                  <option v-for="product in products" :key="product.id" :value="product.id">
                    {{ product.name }}
                  </option>
                </select>
              </td>
              <td class="p-2 border"><input type="number" v-model.number="item.quantity" class="w-full p-1 border rounded" /></td>
              <td class="p-2 border"><input type="number" v-model.number="item.unitPrice" class="w-full p-1 border rounded" /></td>
              <td class="p-2 border"><input type="number" v-model.number="item.gstPercentage" class="w-full p-1 border rounded" /></td>
              <td class="p-2 border"><input type="number" v-model.number="item.discountPercentage" class="w-full p-1 border rounded" /></td>
              <td class="p-2 border">{{ calculateItemTotal(item) }}</td>
              <td class="p-2 border text-center">
                <button @click="removeItem(index)" class="text-red-600 font-bold">&times;</button>
              </td>
            </tr>
          </tbody>
        </table>
        <button class="mt-2 text-blue-600 underline" @click="addItem">+ Add Line Item</button>
      </div>
  
      <!-- Summary Section -->
      <div class="grid grid-cols-3 justify-end mt-4">
        <div></div>
        <div></div>
        <div class="space-y-2">
          <div class="flex justify-between">
            <span>Subtotal:</span>
            <span>{{ subtotal }}</span>
          </div>
          <div class="flex justify-between">
            <span>GST Total:</span>
            <span>{{ gstTotal }}</span>
          </div>
          <div class="flex justify-between">
            <span>Discount Total:</span>
            <span>{{ discountTotal }}</span>
          </div>
          <div class="flex justify-between font-bold">
            <span>Grand Total:</span>
            <span>{{ grandTotal }}</span>
          </div>
        </div>
      </div>
  
      <!-- Action Buttons -->
      <div class="mt-6 space-x-4">
        <button class="bg-blue-600 text-white px-4 py-2 rounded">Save</button>
        <button class="bg-green-600 text-white px-4 py-2 rounded">Print</button>
        <button class="bg-indigo-600 text-white px-4 py-2 rounded">Export PDF</button>
        <button class="bg-gray-500 text-white px-4 py-2 rounded">Cancel</button>
      </div>
  
      <!-- Customer & Product Modal Templates (Placeholder) -->
      <!-- Add your modal components here -->
    </div>
  </template>
  
  <script setup>
  import { reactive, ref, computed } from 'vue'
  
  const invoice = reactive({
    invoiceNumber: '',
    invoiceDate: new Date().toISOString().substr(0, 10),
    customerId: null,
    billingAddress: '',
    shippingAddress: '',
    items: []
  })
  
  const customers = ref([{ id: 1, name: 'ABC Corp' }, { id: 2, name: 'XYZ Ltd' }])
  const products = ref([{ id: 1, name: 'Widget A' }, { id: 2, name: 'Gadget B' }])
  
  function addItem() {
    invoice.items.push({ productId: null, quantity: 1, unitPrice: 0, gstPercentage: 0, discountPercentage: 0 })
  }
  
  function removeItem(index) {
    invoice.items.splice(index, 1)
  }
  
  function calculateItemTotal(item) {
    const base = item.quantity * item.unitPrice
    const gst = (base * item.gstPercentage) / 100
    const discount = (base * item.discountPercentage) / 100
    return (base + gst - discount).toFixed(2)
  }
  
  const subtotal = computed(() => {
    return invoice.items.reduce((sum, item) => sum + item.quantity * item.unitPrice, 0).toFixed(2)
  })
  
  const gstTotal = computed(() => {
    return invoice.items.reduce((sum, item) => sum + (item.quantity * item.unitPrice * item.gstPercentage) / 100, 0).toFixed(2)
  })
  
  const discountTotal = computed(() => {
    return invoice.items.reduce((sum, item) => sum + (item.quantity * item.unitPrice * item.discountPercentage) / 100, 0).toFixed(2)
  })
  
  const grandTotal = computed(() => {
    return invoice.items.reduce((sum, item) => {
      const base = item.quantity * item.unitPrice
      const gst = (base * item.gstPercentage) / 100
      const discount = (base * item.discountPercentage) / 100
      return sum + base + gst - discount
    }, 0).toFixed(2)
  })
  
  function openCustomerModal() {
    // Placeholder for customer modal logic
  }
  
  function openProductModal() {
    // Placeholder for product modal logic
  }
  </script>
  
  <style scoped>
  textarea {
    min-height: 60px;
  }
  </style>
  