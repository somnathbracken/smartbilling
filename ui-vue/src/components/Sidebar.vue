<template>
  <div class="w-64 h-full bg-gray-800 text-white flex flex-col">
    <div class="p-4 text-2xl font-bold border-b border-gray-700">Smart Billing</div>
    <nav class="flex-1 p-4">
      <ul class="space-y-2">
        <li v-for="item in menuItems" :key="item.name">
          <router-link :to="item.route" class="block px-2 py-1 rounded hover:bg-gray-700">{{ item.name }}</router-link>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script setup>
const menuItems = [
  { name: 'Administration', route: '/admin' },
  { name: 'Customers', route: '/customers' },
  { name: 'Vendors', route: '/vendors' },
  { name: 'Products', route: '/products' },
  { name: 'Stock Journal', route: '/stocks' },
  // { name: 'Delivery Challan', route: '/deliveryChallan' },
  // { name: 'Sales Order', route: '/delivery' },
  // { name: 'Purchase Order', route: '/purchase' },
  { name: 'Sales Invoice', route: '/sale' },
  { name: 'Reports', route: '/reports' },
  { name: 'Logout', route: '/logout' }
];
</script>
