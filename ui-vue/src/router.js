import { createRouter, createWebHistory } from 'vue-router'
import CustomerGrid from './views/CustomerGrid.vue'
import VendorGrid from './views/VendorGrid.vue'

const routes = [
  { path: '/', redirect: '/customers' },
  { path: '/customers', component: CustomerGrid },
  { path: '/vendors', component: VendorGrid }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
