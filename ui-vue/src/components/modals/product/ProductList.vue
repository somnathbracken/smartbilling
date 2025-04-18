<template>
  <!-- ---------------------Product Search--------------------- -->
  <div class="flex justify-between items-center mb-4">
  <input
    v-model="search"
    type="text"
    placeholder="Search products..."
    class="input w-1/3 border-gray-300 p-2 rounded focus:ring-blue-400"
  />

  <div class="space-x-2">
    <button @click="exportToExcel" class="btn">Export Excel</button>
    <button @click="exportToPDF" class="btn-red">Export PDF</button>
  </div>
</div>

<!-- ---------------------Product list--------------------- -->
  <div class="p-6 bg-white rounded-xl shadow-md">
    <h2 class="text-2xl font-semibold text-gray-800 mb-6">Product List</h2>

    <div class="overflow-x-auto">
      <table class="min-w-full border border-gray-300 border-collapse text-sm">
        <thead class="bg-gray-100">
          <tr class="divide-x divide-gray-300">
          <th class="px-4 py-2 border border-gray-300 text-left">Name</th>
          <th class="px-4 py-2 border border-gray-300 text-left">Product Code</th>
          <th class="px-4 py-2 border border-gray-300 text-right">MRP</th>
          <th class="px-4 py-2 border border-gray-300 text-right">Qty</th>
          <th class="px-4 py-2 border border-gray-300 text-center">Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(prod, index) in paginatedProducts.filter(p => p && (p.deleted === false || p.deleted === 'false'))" :key="index" class="text-left">
              <td class="px-4 py-2 border border-gray-200 font-medium text-left">{{ prod.name }}</td>
              <td class="px-4 py-2 border border-gray-200 font-medium text-left">{{ prod.productCode }}</td>
              <td class="px-4 py-2 border border-gray-200 text-right">{{ parseFloat(prod.mrp).toFixed(2) }}</td>
              <td class="px-4 py-2 border border-gray-200 text-right">{{ prod.quantity }}</td>
              <td class="px-4 py-2 border border-gray-200 text-center space-x-2">
                <button @click="editProduct(prod)" class="btn-edit">Edit</button>
                <button @click="deleteProduct(prod.id)" class="btn-delete">Delete</button>
              </td>
            </tr>
      </tbody>
    </table>
    </div>
  </div>
  <div class="mt-4 flex justify-center items-center space-x-2">
  <button class="btn" :disabled="currentPage === 1" @click="currentPage--">Prev</button>
  <span class="text-sm">Page {{ currentPage }} of {{ totalPages }}</span>
  <button class="btn" :disabled="currentPage === totalPages" @click="currentPage++">Next</button>
</div>
<div class="flex justify-center mt-4">
    <button
      v-for="page in totalPages"
      :key="page"
      @click="currentPage = page"
      class="pagination-btn"
      :class="{ 'pagination-btn-active': currentPage === page }"
    >
      {{ page }}
    </button>
  </div>
</template>
  
  <script setup>
  import { ref, onMounted, computed } from 'vue'
  import { getAllProducts, deleteProductById } from '../../../services/ProductService';
  //import axios from 'axios'
  defineProps(['products'])
  const emit = defineEmits(['edit-product', 'delete-product'])

  const products = ref([])
    const loadProducts = async () => {
      try {
        const res = await getAllProducts()
        // Make sure it's an array
        products.value = Array.isArray(res.data) ? res.data : []
      } catch (err) {
        console.error('Failed to load products:', err)
        products.value = []
      }
    }
    
    const editProduct = (product) => {
      form.value = { ...product }
      selectedProduct.value = product
      showForm.value = true
    }
    
    const deleteProduct = async (id) => {
      await deleteProductById(id)
      await loadProducts()
    }
    onMounted(loadProducts)

    // ------------------------- Product Search & Pagination of Product --------------------
    const search = ref('');
    const currentPage = ref(1);
    const pageSize = 5;

    // Step 1: Filtered products
    const filtered = computed(() =>
      products.value.filter(p =>
        p.name.toLowerCase().includes(search.value.toLowerCase())||
        p.productCode.toLowerCase().includes(search.value.toLowerCase())
      )
    );

    // Step 2: Paginated products from filtered result
    const paginatedProducts = computed(() =>
      filtered.value.slice((currentPage.value - 1) * pageSize, currentPage.value * pageSize)
    );

    const totalPages = computed(() => Math.ceil(filtered.value.length / pageSize));

    // ------------------------ Export to PDF and Excel -------------------
    import * as XLSX from 'xlsx';
    import jsPDF from 'jspdf';
    import autoTable from 'jspdf-autotable'

    const exportToExcel = () => {
      const worksheet = XLSX.utils.json_to_sheet(filteredProducts.value);
      const workbook = XLSX.utils.book_new();
      XLSX.utils.book_append_sheet(workbook, worksheet, 'Products');
      XLSX.writeFile(workbook, 'products.xlsx');
    };

    const exportToPDF = () => {
      const doc = new jsPDF()
      autoTable(doc, {
        head: [['Name', 'Product Code', 'MRP', 'quantity']],
        body: filteredProducts.value.map(prod => [prod.name, prod.productCode, prod.mrp, prod.quantity]),
      })
      doc.save('vendors.pdf')
    }

  </script>
  
  <style scoped>
/* Add these to your tailwind base layer or a global style file */
.input {
  @apply border border-gray-300 px-3 py-2 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400 transition;
}
.btn {
  @apply bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600;
}
.btn-red {
  @apply bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600;
}
.pagination-btn {
  @apply px-3 py-1 mx-1 border rounded hover:bg-blue-500 hover:text-white transition;
}
.pagination-btn-active {
  @apply bg-blue-500 text-white font-bold;
}
.btn-edit {
  @apply bg-blue-500 text-white px-3 py-1 rounded-md hover:bg-blue-600 shadow-sm transition duration-150 ease-in-out;
}
.btn-delete {
  @apply bg-red-500 text-white px-3 py-1 rounded-md hover:bg-red-600 shadow-sm transition duration-150 ease-in-out;
}

  </style>
  