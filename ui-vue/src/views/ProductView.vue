<template>
    <div class="p-6">
      <h1 class="text-2xl font-bold mb-4">Product Management</h1>
  
      <div class="mb-4">
        <label class="block">Name</label>
        <input v-model="product.name" class="border p-2 w-full" />
      </div>
  
      <div class="mb-4">
        <label class="block">Price</label>
        <input type="number" v-model="product.price" class="border p-2 w-full" />
      </div>
  
      <div class="mb-4">
        <label class="block">Category</label>
        <select v-model="product.categoryId" class="border p-2 w-full">
          <option disabled value="">-- Select Category --</option>
          <option v-for="cat in categories" :key="cat.id" :value="cat.id">{{ cat.name }}</option>
        </select>
  
        <div class="mt-2">
          <input v-model="newCategory" placeholder="New Category" class="border p-2 mr-2" />
          <button @click="addNewCategory" class="bg-green-500 text-white px-4 py-2">Add Category</button>
        </div>
      </div>
  
      <button @click="saveProduct" class="bg-blue-600 text-white px-4 py-2">Save Product</button>
  
      <div class="mt-6">
        <h2 class="text-xl font-semibold">Products</h2>
        <table class="table-auto w-full mt-2 border">
          <thead>
            <tr>
              <th class="border px-4 py-2">Name</th>
              <th class="border px-4 py-2">Price</th>
              <th class="border px-4 py-2">Category</th>
              <th class="border px-4 py-2">Actions</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="prod in productList" :key="prod.id">
              <td class="border px-4 py-2">{{ prod.name }}</td>
              <td class="border px-4 py-2">{{ prod.price }}</td>
              <td class="border px-4 py-2">{{ prod.categoryName }}</td>
              <td class="border px-4 py-2">
                <button @click="editProduct(prod)" class="text-blue-500">Edit</button>
                <button @click="deleteProduct(prod.id)" class="text-red-500 ml-2">Delete</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import ProductService from '../services/ProductService';
  
  const product = ref({ name: '', price: 0, categoryId: '' });
  const productList = ref([]);
  const categories = ref([]);
  const newCategory = ref('');
  
  const loadProducts = async () => {
    const response = await ProductService.getAllProducts();
    productList.value = response.data;
  };
  
  const loadCategories = async () => {
    const response = await ProductService.getCategories();
    categories.value = response.data;
  };
  
  const saveProduct = async () => {
    if (product.value.id) {
      await ProductService.updateProduct(product.value.id, product.value);
    } else {
      await ProductService.addProduct(product.value);
    }
    product.value = { name: '', price: 0, categoryId: '' };
    loadProducts();
  };
  
  const editProduct = (prod) => {
    product.value = { ...prod };
  };
  
  const deleteProduct = async (id) => {
    await ProductService.deleteProduct(id);
    loadProducts();
  };
  
  const addNewCategory = async () => {
    if (newCategory.value.trim()) {
      const response = await ProductService.addCategory(newCategory.value.trim());
      await loadCategories();
      product.value.categoryId = response.data.id;
      newCategory.value = '';
    }
  };
  
  onMounted(() => {
    loadProducts();
    loadCategories();
  });
  </script>
  