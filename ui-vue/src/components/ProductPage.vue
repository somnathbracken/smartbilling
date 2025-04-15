<template>
  <div class="product-form-container">
    <h2>Product Master</h2>
    <form @submit.prevent="saveProduct">
      <div class="grid">
        <div>
          <label>Name</label>
          <input v-model="product.name" type="text" required />
        </div>
        <div>
          <label>Category</label>
          <select v-model="product.category_id">
            <option v-for="cat in categories" :key="cat.id" :value="cat.id">{{ cat.name }}</option>
          </select>
          <input v-model="newCategory" placeholder="Add new category" />
          <button @click.prevent="addCategory">Add</button>
        </div>
        <div>
          <label>SKU</label>
          <input v-model="product.sku" type="text" />
        </div>
        <div>
          <label>Description</label>
          <textarea v-model="product.description"></textarea>
        </div>
        <div>
          <label>Unit Price</label>
          <input v-model="product.unit_price" type="number" step="0.01" />
        </div>
        <div>
          <label>Quantity</label>
          <input v-model="product.quantity" type="number" />
        </div>
        <div>
          <label>GST %</label>
          <input v-model="product.gst_percentage" type="number" step="0.01" />
        </div>
        <div>
          <label>Unit of Measure</label>
          <input v-model="product.unit_of_measure" type="text" />
        </div>
        <div>
          <label>Status</label>
          <select v-model="product.status">
            <option value="Active">Active</option>
            <option value="Inactive">Inactive</option>
          </select>
        </div>
      </div>
      <button type="submit">Save Product</button>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import ProductService from '../services/ProductService'

const product = ref({
  name: '',
  category_id: '',
  sku: '',
  description: '',
  unit_price: 0,
  quantity: 0,
  gst_percentage: 0,
  unit_of_measure: '',
  status: 'Active'
})

const categories = ref([])
const newCategory = ref('')

onMounted(async () => {
  categories.value = await ProductService.getCategories()
})

const addCategory = async () => {
  if (newCategory.value.trim()) {
    const added = await ProductService.addCategory({ name: newCategory.value })
    categories.value.push(added)
    product.value.category_id = added.id
    newCategory.value = ''
  }
}

const saveProduct = async () => {
  await ProductService.saveProduct(product.value)
  alert('Product saved successfully!')
}
</script>

<style scoped>
.product-form-container {
  max-width: 800px;
  margin: 0 auto;
  text-align: left;
}

.grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 16px;
}
</style>