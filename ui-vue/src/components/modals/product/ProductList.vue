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

    <!-- -------------------- Status Toggle switch code -------------------- -->
    <div class="flex items-center space-x-2 mb-4">
      <label class="text-sm font-medium text-gray-700">Show Deleted</label>
      <label class="relative inline-flex items-center cursor-pointer">
        <input type="checkbox" v-model="showDeleted" class="sr-only peer">
        <div
          class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-2 peer-focus:ring-blue-500 rounded-full peer peer-checked:bg-green-500 transition"
        ></div>
        <div
          class="absolute left-0.5 top-0.5 w-5 h-5 bg-white rounded-full shadow-md transform peer-checked:translate-x-full transition"
        ></div>
      </label>
    </div>
    <!-- -------------------- Status Toggle switch finished -------------------- -->
    <div class="overflow-x-auto">
      <table class="min-w-full border border-gray-300 border-collapse text-sm">
        <thead class="bg-gray-100">
          <tr class="divide-x divide-gray-300">
          <th class="px-4 py-2 border border-gray-300 text-left">Name</th>
          <th class="px-4 py-2 border border-gray-300 text-left">Product Code</th>
          <th class="px-4 py-2 border border-gray-300 text-right">MRP</th>
          <th class="px-4 py-2 border border-gray-300 text-right">Qty</th>
          <th class="px-4 py-2 border border-gray-300 text-right">Status</th>
          <th class="px-4 py-2 border border-gray-300 text-center">Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(prod, index) in paginatedProducts" :key="index" class="text-left">
              <td class="px-4 py-2 border border-gray-200 font-medium text-left">{{ prod.name }}</td>
              <td class="px-4 py-2 border border-gray-200 font-medium text-left">{{ prod.productCode }}</td>
              <td class="px-4 py-2 border border-gray-200 text-right">{{ parseFloat(prod.mrp).toFixed(2) }}</td>
              <td class="px-4 py-2 border border-gray-200 text-right">{{ prod.quantity }}</td>
              <td class="px-4 py-2 border border-gray-200 font-medium text-left">{{ prod.deleted }}</td>
              <td class="px-4 py-2 border border-gray-200 text-center space-x-2">
                <button @click="openEditModal(prod)" class="btn px-2 py-1 bg-blue-500 text-white rounded hover:bg-blue-600">Edit</button>
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
  <EditProductModal v-if="showEditModal" :product="editableProduct" @close="showEditModal = false" @update="handleProductUpdate"/>
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

    //----------------------------------- Product Search & Pagination of Product & Status Toggle Switch ------------------------------------------
    const search = ref('');
    const currentPage = ref(1);
    const pageSize = 5;
    const showDeleted = ref(false)
    
    const filtered = computed(() => {
      console.log("Products value:",products.value);
      console.log("Toggle value:", showDeleted.value);
      console.log("Filtered result:", filtered.value);
      return products.value.filter(p => {
        const matchesSearch =
        p.name.toLowerCase().includes(search.value.toLowerCase())||
        p.productCode.toLowerCase().includes(search.value.toLowerCase())

        const isDeleted = p.deleted === true || p.deleted === 'true';
        const isActive = p.deleted === false || p.deleted === 'false';

        const matchesStatus = showDeleted.value ? isDeleted : isActive;

        return matchesSearch && matchesStatus;
      });
    });

    const paginatedProducts = computed(() =>
      filtered.value.slice((currentPage.value - 1) * pageSize, currentPage.value * pageSize)
    );

    const totalPages = computed(() => Math.ceil(filtered.value.length / pageSize));

//----------------------------------- End of Product Search & Pagination of Product & Status Toggle Switch ------------------------------------------

//------------------------------------ Product Edit in Modal Popup --------------------------------------------

    import EditProductModal from './EditProductModal.vue'
    
    const showEditModal = ref(false);
    const editableProduct = ref({});

    function openEditModal(prod) {
      editableProduct.value = { ...prod }; // clone to avoid two-way binding side effects
      showEditModal.value = true;
    }

    async function updateProduct() {
      try {
        // Make your PUT request here to update the product
        await axios.put(`/api/products/${editableProduct.value.id}`, editableProduct.value);
        showEditModal.value = false;

        // Refresh product list or update locally
        const index = products.value.findIndex(p => p.id === editableProduct.value.id);
        if (index !== -1) {
          products.value[index] = { ...editableProduct.value };
        }

        alert('Product updated successfully!');
      } catch (error) {
        console.error(error);
        alert('Update failed.');
      }
    }

    function handleProductUpdate(updatedProduct) {
      const index = products.value.findIndex(p => p.id === updatedProduct.id);
      if (index !== -1) {
        products.value[index] = updatedProduct;
      }
      showEditModal.value = false;
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

/* For status Toggle Switch */
.toggle-checkbox {
  @apply h-5 w-10 rounded-full appearance-none bg-gray-300 checked:bg-green-500 transition duration-300 relative;
  background-position: left center;
  background-repeat: no-repeat;
}

.toggle-checkbox:checked::before {
  content: '';
  @apply w-4 h-4 bg-white rounded-full absolute left-5 top-0.5 transform -translate-x-full transition-transform duration-300;
}

  </style>
  