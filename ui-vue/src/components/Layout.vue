<template>
    <div class="flex min-h-screen">
      <nav class="w-64 bg-gray-800 text-white p-4">
        <ul>
          <li><router-link to="/" class="block py-2 px-4">Customers</router-link></li>
          <li><router-link to="/vendors" class="block py-2 px-4">Vendors</router-link></li>
          <li><router-link to="/products" class="block py-2 px-4">Products</router-link></li>
          <li><router-link to="/stocks" class="block py-2 px-4">Stocks</router-link></li>
          <li><router-link to="/purchase" class="block py-2 px-4">Purchase</router-link></li>
          <li><router-link to="/sale" class="block py-2 px-4">Sale</router-link></li>
          <li><router-link to="/purchase-invoice" class="block py-2 px-4">Purchase Invoice</router-link></li>
          <li><router-link to="/sales-invoice" class="block py-2 px-4">Sales Invoice</router-link></li>
          <li><router-link to="/reports" class="block py-2 px-4">Reports</router-link></li>
          <li><router-link to="/logout" class="block py-2 px-4">Logout</router-link></li>
        </ul>
      </nav>
      <div class="flex-1 p-4">
        <header class="bg-blue-600 text-white p-4">
          <h1 class="text-2xl">Customer Management Dashboard</h1>
        </header>
        <main class="mt-4">
          <router-view />
        </main>
        <footer class="bg-gray-800 text-white p-4 text-center mt-auto">
          <p>&copy; 2025 Your Company</p>
        </footer>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'Layout',
  };
  </script>
  